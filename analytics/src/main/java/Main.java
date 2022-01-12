import javax.jms.*;

public class Main {

    public static void main(String[] args) throws JMSException {

        BrokerReceiver brokerReceiver = new BrokerReceiver();
        brokerReceiver.receiver(args[0]);

    }
}