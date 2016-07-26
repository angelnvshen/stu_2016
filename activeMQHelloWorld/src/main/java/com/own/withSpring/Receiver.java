package com.own.withSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.Map;

/**
 * Created by CHANEL on 2016/6/1.
 */
public class Receiver {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");
        JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
        while (true){
            Map<String, Object> map = (Map<String, Object>) template.receiveAndConvert();
            System.out.println("收到消息：" + map.get("message"));
        }
    }
}
