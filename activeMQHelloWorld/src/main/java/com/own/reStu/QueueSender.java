package com.own.reStu;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by CHANEL on 2016/5/31.
 */
public class QueueSender {

    // 发送次数
    public static final int SEND_NUM = 5;
    public static final String QueueName = "helloQueue";

    // 发送消息
    public static void sendMessage(QueueSession session, javax.jms.QueueSender sender) throws JMSException {
        for (int i = 0; i < SEND_NUM; i++) {
            String message = "发送消息第" + (i + 1) + "条";
            MapMessage map = session.createMapMessage();
            map.setString("text", message);
            map.setLong("time", System.currentTimeMillis());
            sender.send(map);
        }
    }

    public static void run() throws JMSException {
        QueueConnection connection = null;
        QueueSession session = null;

        QueueConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                ActiveMQConnection.DEFAULT_BROKER_URL);

        try {
            connection = factory.createQueueConnection();

            connection.start();

            session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            // 创建一个消息队列
            Queue queue = session.createQueue(QueueName);

            javax.jms.QueueSender sender = session.createSender(queue);

            sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            sendMessage(session, sender);

            session.commit();
        } catch (JMSException e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        QueueSender.run();
    }
}
