package json.reader;

import com.google.gson.*;
import domain.Client;

import java.lang.reflect.Type;

public class ClientRead implements JsonDeserializer<Client> {

    @Override
    public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject asJsonObject = json.getAsJsonObject();
        Client client = new Client();
        client.setName(asJsonObject.get("name").getAsString());
        client.setBlocked(asJsonObject.get("isBlocked").getAsBoolean());
        return client;
    }
}
