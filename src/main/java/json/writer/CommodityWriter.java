package json.writer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import domain.Commodity;

import java.lang.reflect.Type;

public class CommodityWriter implements JsonSerializer<Commodity> {

    @Override
    public JsonElement serialize(Commodity commodity, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        double price = commodity.getPrice();
        String serializePrice = "$"+price;
        result.add("art", context.serialize(commodity.getArt()));
        result.addProperty("price", serializePrice);
        result.add("description", context.serialize(commodity.getDescription()));
        return result;
    }
}
