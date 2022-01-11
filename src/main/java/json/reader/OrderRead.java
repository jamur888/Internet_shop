package json.reader;

import com.google.gson.*;
import domain.Client;
import domain.Commodity;
import domain.Order;

import java.lang.reflect.Type;

public class OrderRead implements JsonDeserializer<Order> {
    @Override
    public Order deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject asJsonObject = json.getAsJsonObject();
        Order order = new Order();
        order.setCommodity_id(context.deserialize(asJsonObject.get("commodity"), Commodity.class));
        order.setClient_id(context.deserialize(asJsonObject.get("client"), Client.class));
        return order;
    }
}
