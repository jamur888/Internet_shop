package json.writer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import domain.Order;

import java.lang.reflect.Type;

public class OrderWriter implements JsonSerializer<Order> {

    @Override
    public JsonElement serialize(Order order, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("number", context.serialize(order.getId()));
        result.add("commodity", context.serialize(order.getCommodity_id()));
        return result;
    }
}
