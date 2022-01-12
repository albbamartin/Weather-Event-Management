import javax.jms.JMSException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                String url = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=66c6ed3c894ba351e103b427d3afd5af&units=metric";

                OpenWeatherMapSensor openWeatherMapSensor = new OpenWeatherMapSensor();
                Weather event = openWeatherMapSensor.get(url);

                try {

                    BrokerSender brokerSender = new BrokerSender();
                    brokerSender.sender(event);

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,0,15*60*1000);

    }
}
