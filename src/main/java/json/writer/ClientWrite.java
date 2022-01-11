package json.writer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import domain.Client;

import java.lang.reflect.Type;

public class ClientWrite implements JsonSerializer<Client> {
    @Override
    public JsonElement serialize(Client client, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("name", client.getName());
        result.addProperty("isBlocked", client.isBlocked());
        result.add("orders", jsonSerializationContext.serialize(client.getOrders()));
        return result;
    }
}
