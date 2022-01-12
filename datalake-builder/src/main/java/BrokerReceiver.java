import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.File;
import java.io.IOException;

public class BrokerReceiver implements Receiver{

    private final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public Session createBrokerSession() throws JMSException {
        Connection connection = new ActiveMQConnectionFactory(url).createConnection();
        connection.setClientID("dl-builder");
        connection.start();
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Override
    public MessageConsumer messageConsumer() throws JMSException {
        Session session = createBrokerSession();
        Topic topic = session.createTopic("sensor.Weather");
        return session.createDurableSubscriber(topic, "datalake-builder");
    }

    @Override
    public void receiver(String directory) throws JMSException {

        MessageConsumer consumer = messageConsumer();

        consumer.setMessageListener(message -> {

            TextMessage textMessage = (TextMessage) message;

            try {

                String event = textMessage.getText();

                WeatherEventFileManager fw = new WeatherEventFileManager(directory, event);
                fw.writer();

            } catch (IOException | JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
