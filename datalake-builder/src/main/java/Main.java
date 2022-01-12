import javax.jms.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws JMSException, IOException {

        String directory = getFileDirectory(args[0]);

        BrokerReceiver brokerReceiver = new BrokerReceiver();
        brokerReceiver.receiver(directory);

    }

    public static String getFileDirectory(String path) throws IOException{

        String directory = path + "/datalake/events/sensor.Weather/";

        if (!Files.exists(Path.of(directory))) {
            Files.createDirectories(Path.of(directory));
        }

        return directory;
    }

}