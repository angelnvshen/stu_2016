package active.mq.consumer.queue;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by bf50 on 2016/2/24.
 * 队列消息监听器
 */
@Component
public class QueueReceiver2 implements MessageListener{
    public void onMessage(Message message) {
        try {
            System.out.println("QueueReceiver2接收到消息:"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
