import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WeatherEventFileManager implements WeatherEventFile {
    String directory;
    String event;

    public WeatherEventFileManager(String directory, String event) {
        this.directory = directory;
        this.event = event;
    }

    @Override
    public void writer() throws IOException {

        String path = this.directory + getFilename() + ".json";
        File file = getFile(path);

        FileWriter fw = new FileWriter(file, true);
        fw.write(this.event + "\n");
        fw.flush();
        fw.close();

    }

    @Override
    public String getFilename() {

        String ts = new Gson().fromJson(this.event, JsonObject.class).get("ts").getAsString();
        Instant now = Instant.parse(ts);
        LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneOffset.UTC);

        return dateTime.getYear() + "" + dateTime.getMonthValue() + "" + dateTime.getDayOfMonth() + "" + dateTime.getHour();

    }
}
