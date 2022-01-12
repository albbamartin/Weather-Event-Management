import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public interface Receiver {

    MessageConsumer messageConsumer() throws JMSException;
    Session createBrokerSession() throws JMSException;
    void receiver(String path) throws JMSException;

}