package com.own.reStu;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by CHANEL on 2016/5/31.
 */
public class MessageSender {

    // 发送次数
    public static final int SEND_NUM = 5;

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址


    public static final String DESTINATION = "helloAtiveMQ";

    public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
        for(int i =0;i<SEND_NUM;i++){
            String message = "发送消息第" + (i + 1) + "条";
            TextMessage textMessage = session.createTextMessage(message);
            System.out.println(message);
            producer.send(textMessage);
        }
    }

    public static void run() throws JMSException {
        Connection connection = null;
        Session session = null;
        //创建连接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        // 通过工厂创建一个连接
        try {
            connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建一个会话
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(DESTINATION);
            // 创建消息提供者
            MessageProducer producer = session.createProducer(destination);
            // 设置持久化模式
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            sendMessage(session, producer);
            // 提交会话
            session.commit();

        } catch (JMSException e) {
            throw e;
        } finally {
            // 关闭资源
            if(session != null)
                session.close();

            if(connection != null)
                connection.close();
        }
    }

    public static void main(String[] args) throws JMSException {
        MessageSender.run();
    }
}
