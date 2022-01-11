package json.reader;

import com.google.gson.*;
import domain.Commodity;

import java.lang.reflect.Type;

public class CommodityRead implements JsonDeserializer<Commodity> {


    @Override
    public Commodity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject asJsonObject = new JsonObject();
        final String priceString = asJsonObject.get("price").getAsString();
        final String newPriceString = priceString.replace("$", "");
        Commodity commodity = new Commodity();
        commodity.setArt(asJsonObject.get("art").getAsInt());
        commodity.setPrice(Double.parseDouble(newPriceString));
        commodity.setDescription(asJsonObject.get("description").getAsString());
        return commodity;
    }
}
