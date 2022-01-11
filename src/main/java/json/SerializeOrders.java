package json;

import com.google.gson.GsonBuilder;
import domain.Order;
import menu.Menu;
import servise.OrderServise;

import java.util.List;
import java.util.Scanner;

public class SerializeOrders extends SerializeToJson implements Menu {
    private final Scanner scanner = new Scanner(System.in);

    private static SerializeOrders instance;

    public static SerializeOrders getInstance() {
        if (instance == null) {
            instance = new SerializeOrders();
        }
        return instance;
    }

    private SerializeOrders() {

    }

    @Override
    public void getMenu() {
        System.out.println("Введите путь к файлу .json");
        String path = scanner.next();
        gson = new GsonBuilder().setPrettyPrinting().create();
        String propName = "orders";
        final List<Order> allOrders = OrderServise.getOrderServise().getAll();
        serialize(allOrders, path);
    }
}
