import java.io.File;
import java.io.IOException;

public interface WeatherEventFile {

    void writer() throws IOException;

    String getFilename();

    default File getFile(String path) throws IOException {

        File file = new File(path);

        if (!file.exists()) {
            file.createNewFile();
        }

        return file;

    }

}
