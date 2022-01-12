import com.google.gson.*;
import java.time.Instant;

public class Serializer {
    public String serialize(Weather event) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString())).create();
        return gson.toJson(event);
    }
}

