import java.time.Instant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class WeatherBuilder {
    
    private JsonObject object;

    public WeatherBuilder() {
        this.object = null;
    }

    public Weather extraction(String response) {

        this.object = new Gson().fromJson(response, JsonObject.class);

        return new Weather()
                .setTs(Instant.ofEpochSecond(this.object.get("dt").getAsLong()))
                .setLocation(new WeatherLocation(this.object.get("coord").getAsJsonObject().get("lon").getAsDouble(), this.object.get("coord").getAsJsonObject().get("lat").getAsDouble()))
                .setWeather(this.object.get("weather").getAsJsonArray().get(Integer.parseInt("0")).getAsJsonObject().get("main").getAsString())
                .setPressure(this.object.get("main").getAsJsonObject().get("pressure").getAsDouble())
                .setHumidity(this.object.get("main").getAsJsonObject().get("humidity").getAsDouble())
                .setTemp(this.object.get("main").getAsJsonObject().get("temp").getAsDouble())
                .setWind(this.object.get("wind").getAsJsonObject().get("speed").getAsDouble())
                .setWindDirection(this.object.get("wind").getAsJsonObject().get("deg").getAsDouble());
    }
}