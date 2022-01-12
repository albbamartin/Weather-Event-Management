import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jfree.data.xy.XYSeriesCollection;
import javax.jms.*;
import java.sql.SQLException;

public class BrokerReceiver implements Receiver {

    private final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public Session createBrokerSession() throws JMSException {
        Connection connection = new ActiveMQConnectionFactory(url).createConnection();
        connection.setClientID("1234");
        connection.start();
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Override
    public MessageConsumer messageConsumer() throws JMSException {
        Session session = createBrokerSession();
        Topic topic = session.createTopic("sensor.Weather");
        return session.createDurableSubscriber(topic, "analytics");
    }

    @Override
    public void receiver(String path) throws JMSException {

        MessageConsumer consumer = messageConsumer();
        consumer.setMessageListener(message -> {

            TextMessage textMessage = (TextMessage) message;

            WeatherBuilder weatherBuilder = new WeatherBuilder();
            DatabaseManager databaseManager = new DatabaseManager(path);

            try {

                Weather weather = weatherBuilder.extraction(textMessage.getText());

                databaseManager.createTable();
                databaseManager.dataInserter(
                        weather.getTs().toEpochMilli(),
                        weather.getLocation().getLat(),
                        weather.getLocation().getLon(),
                        weather.getTemp(),
                        weather.getPressure(),
                        weather.getHumidity()
                );

            } catch (SQLException | JMSException e) {
                e.printStackTrace();
            }

            WeatherLineChart weatherLineChart = new WeatherLineChart(path);
            XYSeriesCollection dataset = weatherLineChart.addValues();
            weatherLineChart.createChart(dataset);

            ExportDatabaseToExcel exportDatabaseToExcel = new ExportDatabaseToExcel();
            exportDatabaseToExcel.exporter(path);

            PythonScriptLauncher pythonScriptLauncher = new PythonScriptLauncher();
            pythonScriptLauncher.launcher();

        });
    }
}
