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
        return new DirectExchange("directExchange");


    }

    @Bean
    public DirectExchange defaultExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange","exchange");
        return  new DirectExchange("directExchangeTest2",false,false,map);
    }


    @Bean
    public Queue dealQueue() {
        Map<String,Object> map = new HashMap<>();
        //设置消息的过期时间 单位毫秒
        map.put("x-message-ttl", 10000);
        //设置附带的死信交换机
        map.put("x-dead-letter-exchange","exchange");
        //指定重定向的路由建 消息作废之后可以决定需不需要更改他的路由建 如果需要 就在这里指定
        map.put("x-dead-letter-routing-key","dead.order");
        return new Queue("directExchange", true,false,false,map);



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

    @Bean
    public Queue manualQueue() {
        return new Queue("manualQueue", false);
    }


    @Bean
    public Binding manualBind() {

        return  BindingBuilder.bind(manualQueue()).to(directExchange()).with("manual");
    }


}
