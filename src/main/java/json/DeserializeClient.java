package json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import domain.Client;
import domain.Order;
import json.reader.ClientRead;
import menu.Menu;
import servise.ClientServise;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class DeserializeClient implements Menu {

    private static DeserializeClient menu;
    private final Scanner scanner = new Scanner(System.in);

    private DeserializeClient() {
    }

    public static DeserializeClient getInstance() {
        if (menu == null) {
            menu = new DeserializeClient();
        }
        return menu;
    }

    @Override
    public void getMenu() {

        System.out.println("Введите путь к файлу .json:");
        String path = scanner.next();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Client.class, new ClientRead())
                .registerTypeAdapter(Order.class, new ClientRead())
                .create();
        try (Reader reader = new FileReader(path)) {
            final JsonElement jsonElement = JsonParser.parseReader(reader);
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            final JsonElement jsonElement1 = asJsonObject.get("clients");
            final JsonArray asJsonArray = jsonElement1.getAsJsonArray();
            List<Client> clientsFromJson = gson.fromJson(asJsonArray, new TypeToken<List<Client>>() {
            }.getType());
//                Проверка на уникальность заказа
            final List<Client> clientsFromDb = ClientServise.getClientServise().getAll();
            List<Client> toDB = clientsFromJson.stream().filter(s1 -> clientsFromDb.stream()
                    .noneMatch(s2 -> isSame(s1, s2))).collect(toList());
            toDB.forEach(clients -> {
                try {
                    ClientServise.getClientServise().create(clients);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Загрузка завершена...");
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + e);
            System.out.println(("Не удается найти указанный json файл - ") + path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean isSame(Client o1, Client o2) {
        return o1.getName() == o2.getName()
                && o1.isBlocked() == o2.isBlocked()
                && o1.getOrders() == o2.getOrders();

    }
}
