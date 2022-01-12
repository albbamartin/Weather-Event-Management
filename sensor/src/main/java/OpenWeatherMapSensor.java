import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class OpenWeatherMapSensor implements Sensor {

    private final HttpClient httpClient;
    private HttpGet get;
    private String resource;

    public OpenWeatherMapSensor() {
        this.httpClient = HttpClients.createDefault();
        this.get = null;
        this.resource = null;
    }

    @Override
    public Weather get(String url){
        this.get = new HttpGet(url);

        try{
            HttpResponse response = this.httpClient.execute(this.get);
            this.resource = EntityUtils.toString(response.getEntity());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        WeatherBuilder weatherBuilder = new WeatherBuilder();
        return weatherBuilder.extraction(this.resource);
    }
}