package json;


import com.google.gson.GsonBuilder;
import domain.Commodity;
import menu.Menu;
import servise.CommodityServise;

import java.util.List;
import java.util.Scanner;

public class CommoditySerializer extends SerializeToJson implements Menu {
    private final Scanner scanner = new Scanner(System.in);
    private static CommoditySerializer instance;

    public static CommoditySerializer getInstance() {
        if (instance == null) {
            instance = new CommoditySerializer();
        }
        return instance;
    }

    private CommoditySerializer() {
    }

    @Override
    public void getMenu() {
        System.out.println("Введите путь к файлу .json");
        String path = scanner.next();
        gson = new GsonBuilder().setPrettyPrinting().create();
        propName = "commodity";
        final List<Commodity> allCommodity = CommodityServise.getCommodityServise().getAll();
        serialize(allCommodity, path);
    }
}
