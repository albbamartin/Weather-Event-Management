import java.io.File;
import java.io.IOException;

public class FileGenerator {

    public File filePath(String path) throws IOException {

        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        
        return file;
    }
}