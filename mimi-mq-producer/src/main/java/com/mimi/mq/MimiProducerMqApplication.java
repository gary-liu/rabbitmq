package com.mimi.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MimiProducerMqApplication {

    public static void main(String[] args) {

        SpringApplication.run(MimiProducerMqApplication.class, args);

        /*模拟发送消息确认失败*/
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MqConfig.class);
//
//        ProduceMq produceMq = applicationContext.getBean("produceMq",ProduceMq.class);
//        produceMq.sendConfirmMessage("test");

    }

}
