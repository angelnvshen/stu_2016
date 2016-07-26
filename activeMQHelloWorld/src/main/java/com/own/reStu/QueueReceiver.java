package com.own.reStu;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by CHANEL on 2016/5/31.
 */
public class QueueReceiver {

    public static final String QueueName = "helloQueue";

    public static void run() throws Exception {
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

            Queue queue = session.createQueue(QueueName);

            javax.jms.QueueReceiver receiver = session.createReceiver(queue);

            receiver.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    if(message != null){
                        MapMessage map = (MapMessage) message;
                        try {
                            System.out.println(map.getLong("time") + "接收#" + map.getString("text"));
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            // 休眠100ms再关闭
            Thread.sleep(1000 * 100);

            // 提交会话
            session.commit();

        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭释放资源
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        QueueReceiver.run();
    }
}
