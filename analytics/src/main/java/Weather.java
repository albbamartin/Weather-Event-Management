import java.time.Instant;

public class Weather {

    private Instant ts;
    private WeatherLocation location;
    private Double temp;
    private Double pressure;
    private Double humidity;

    public Instant getTs() {
        return ts;
    }

    public Weather setTs(Instant ts) {
        this.ts = ts;
        return this;
    }

    public WeatherLocation getLocation() {
        return location;
    }

    public Weather setLocation(WeatherLocation location) {
        this.location = location;
        return this;
    }

    public Double getTemp() {
        return temp;
    }

    public Weather setTemp(Double temp) {
        this.temp = temp;
        return this;

    }

    public Double getPressure() {
        return pressure;
    }

    public Weather setPressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Weather setHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }
}
