import java.time.Instant;

public class Weather {

    private Instant ts;
    private WeatherLocation location;
    private String weather;
    private Double temp;
    private Double wind;
    private Double windDirection;
    private Double pressure;
    private Double humidity;

    public Weather setTs(Instant ts) {
        this.ts = ts;
        return this;
    }

    public Weather setLocation(WeatherLocation location) {
        this.location = location;
        return this;
    }

    public Weather setWeather(String weather) {
        this.weather = weather;
        return this;
    }

    public Weather setTemp(Double temp) {
        this.temp = temp;
        return this;
    }

    public Weather setWind(Double wind) {
        this.wind = wind;
        return this;
    }

    public Weather setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public Weather setPressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    public Weather setHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }
}

