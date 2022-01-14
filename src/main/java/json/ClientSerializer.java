package json;

import com.google.gson.GsonBuilder;
import domain.Client;
import domain.Commodity;
import domain.Order;
import json.writer.CommodityWriter;
import json.writer.OrderWriter;
import menu.Menu;
import servise.ClientServise;
import servise.OrderServise;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClientSerializer extends SerializeToJson implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private static ClientSerializer instance;

    public static ClientSerializer getInstance() {
        if (instance == null) {
            instance = new ClientSerializer();
        }
        return instance;
    }

    private ClientSerializer() {
    }

    @Override
    public void getMenu() throws SQLException {

        System.out.println("Введите путь к файлу .json");
        String path = scanner.next();
        gson = new GsonBuilder()
                .registerTypeAdapter(Order.class, new OrderWriter())
                .setPrettyPrinting().create();
        propName = "client";
        final List<Client> allClients = ClientServise.getClientServise().getAll();
        serialize(allClients, path);

    }
}
