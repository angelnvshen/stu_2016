package com.own.reStu;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by CHANEL on 2016/5/31.
 */
public class MessageReceiver {

    public static final String DESTINATION = "helloAtiveMQ";

    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void run() throws JMSException {
        Connection connection = null;
        Session session = null;

        ConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        try {
            connection = factory.createConnection();

            connection.start();

            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(DESTINATION);

            // 消息的消费者
            MessageConsumer consumer = session.createConsumer(destination);

            while (true){
                // 接收数据的时间（等待）100ms
                Message message = consumer.receive(1000*100);
                TextMessage textMessage = (TextMessage) message;
                if(textMessage != null)
                    System.out.println("接收：" + textMessage.getText());
                else
                    break;
            }

            session.commit();


        } catch (JMSException e) {
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
        MessageReceiver.run();
    }

}
