package json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public abstract class SerializeToJson {
    protected Gson gson;
    protected String propName;


    protected <T extends Order> void serialize(List<Order> allOrders, String filePathName) {
        try (Writer writer = new FileWriter(filePathName)) {
            JsonObject jsonObject = new JsonObject();
            gson.toJson(jsonObject, writer);
            System.out.println("Загрузка завершена...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось загрузить инормацию в json файл...");
        }
    }
}
