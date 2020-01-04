package com.mimi.mq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MqConfig {

//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConfirmCallback(new MyConfirmCallback());
//        rabbitTemplate.setReturnCallback(new MyCallBack());
//        return rabbitTemplate;
//
//    }


    @Bean
    public DirectExchange directExchange() {
//        创建一个direct类型的交换机
//        return new DirectExchange("directExchange");

        Map<String, Object> argsMap = new HashMap<>();
        /*声明一个备用交换机  备用交换机必须是已经存在的交换机  有备用交换机时
        * 调用失败时不会调用失败回调 只有在备用交换机也失败时才会调用失败回
        * 调*/
        argsMap.put("alternate-exchange", "fanoutExchange");
        return new DirectExchange("directExchange", true, false, argsMap);
    }

    @Bean
    public Queue queue() {
        //声明一个队列           名字              是否持久化
        return new Queue("testQueue", true);
    }

    @Bean
    public Binding binding() {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct.key");
    }


}
