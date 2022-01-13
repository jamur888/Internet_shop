package json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.Entity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public abstract class SerializeToJson {
    protected Gson gson;
    protected String propName;

    protected <T extends Entity> void serialize(List<T> data, String filePathName) {
        try (Writer writer = new FileWriter(filePathName)) {
            final JsonElement je = gson.toJsonTree(data);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(propName, je);
            gson.toJson(jsonObject, writer);
            System.out.println("Загрузка завершена...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось загрузить информацию в json файл...");
        }
    }

}
