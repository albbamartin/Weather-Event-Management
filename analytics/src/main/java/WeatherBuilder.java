import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.Instant;

public class WeatherBuilder {

    private JsonObject object;

    public WeatherBuilder() {
        this.object = null;
    }

    public Weather extraction(String event) {

        this.object = new Gson().fromJson(event, JsonObject.class);
        return new Weather()
            .setTs(Instant.parse(this.object.get("ts").getAsString()))
            .setLocation(new WeatherLocation(this.object.get("location").getAsJsonObject().get("lat").getAsDouble(), this.object.get("location").getAsJsonObject().get("lon").getAsDouble()))
            .setTemp(this.object.get("temp").getAsDouble())
            .setPressure(this.object.get("pressure").getAsDouble())
            .setHumidity(this.object.get("humidity").getAsDouble());
    }
}