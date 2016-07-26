package active.mq.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by bf50 on 2016/2/24.
 * Topic消息监听器
 */
@Component
public class TopicReceiver1 implements MessageListener{

    public void onMessage(Message message) {
        try {
            System.out.println("TopicReceiver1接收到消息:"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
