package json;

import com.google.gson.*;
import domain.*;
import menu.Menu;
import servise.OrderServise;
import json.writer.*;

import java.util.List;
import java.util.Scanner;

public class OrdersSerializer extends SerializeToJson implements Menu {
    private final Scanner scanner = new Scanner(System.in);

    private static OrdersSerializer menu;

    public static OrdersSerializer getInstance() {
        if (menu == null) {
            menu = new OrdersSerializer();
        }
        return menu;
    }

    private OrdersSerializer() {

    }

    @Override
    public void getMenu() {

        System.out.println("Введите путь к файлу .json");
        String path = scanner.next();
        gson = new GsonBuilder()
                .registerTypeAdapter(Client.class, new ClientWrite())
                .registerTypeAdapter(Order.class, new OrderWriter())
                .registerTypeAdapter(Commodity.class, new CommodityWriter())
                .setPrettyPrinting().create();
        propName = "orders";
        final List<Order> allOrders = OrderServise.getOrderServise().getAll();
        serialize(allOrders, path);

    }
}