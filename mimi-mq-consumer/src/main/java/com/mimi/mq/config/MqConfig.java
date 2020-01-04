package com.mimi.mq.config;


import com.mimi.mq.callback.MyCallBack;
import com.mimi.mq.callback.MyConfirmCallback;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListener = new SimpleRabbitListenerContainerFactory();
        //这个connectionFactory就是我们自己配置的连接工厂直接注入进来
        simpleRabbitListener.setConnectionFactory(connectionFactory);
        //这边设置消息确认方式由自动确认变为手动确认
        simpleRabbitListener.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置消息预取的数量
        /* 这个数值的大小与性能成正比 但是有上限，与数据可靠性,客户端的利用率成反比 */
        simpleRabbitListener.setPrefetchCount(1);
        return simpleRabbitListener;

    }



}
