public class WeatherLocation {
    private final Double lon;
    private final Double lat;

    public WeatherLocation(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }
}