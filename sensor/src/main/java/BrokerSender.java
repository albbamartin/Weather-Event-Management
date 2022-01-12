import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class BrokerSender implements Sender {

    private final String url;
    private String serializedEvent;

    public BrokerSender() {
        this.url = ActiveMQConnection.DEFAULT_BROKER_URL;
        this.serializedEvent = null;
    }

    @Override
    public void sender(Weather event) throws JMSException {

        Connection connection = new ActiveMQConnectionFactory(this.url).createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("sensor.Weather");
        MessageProducer producer = session.createProducer(destination);

        Serializer serializer = new Serializer();
        this.serializedEvent = serializer.serialize(event);

        TextMessage message = session.createTextMessage(this.serializedEvent);
        producer.send(message);
        connection.close();
    }
}
