package com.mimi.mq.config;


import com.mimi.mq.callback.MyCallBack;
import com.mimi.mq.callback.MyConfirmCallback;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {



    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //开启mandatory模式（开启失败回调）
        rabbitTemplate.setMandatory(true);
        //指定失败回调接口的实现类
        rabbitTemplate.setReturnCallback(new MyCallBack());
        rabbitTemplate.setConfirmCallback(new MyConfirmCallback());

        return rabbitTemplate;
    }

//    这个ConnectionFactory 是使用javaconfig方式配置连接的时候才需要传入的
//    如果是yml配置 的连接的话是不需要的

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("gary");
        connectionFactory.setPassword("123456");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("localtest");
//是否开启消息确认机制
//        connectionFactory.setPublisherConfirms(true);

        return connectionFactory;
    }



}
