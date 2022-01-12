import javax.jms.JMSException;

public interface Sender {
    void sender(Weather response) throws JMSException;
}