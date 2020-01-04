package com.mimi.mq.produce;

import com.mimi.mq.callback.MyCallBack;
import com.mimi.mq.callback.MyMessageConverter;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProduceMq {

    @Autowired
    RabbitTemplate template;

    @PostConstruct
    public void init() {
        template.setReturnCallback(new MyCallBack());
    }

    public void sendMessage(String message) {
//        template.convertAndSend(message);
//        template.setReturnCallback(new MyCallBack());

        template.convertAndSend("directExchange", "direct.key",message);

    }
    public void sendManualMessage(String message) {
//        template.convertAndSend(message);


        template.convertAndSend("directExchange", "manual",message);

    }

    public void sendConfirmMessage(String message) {
//        template.setConfirmCallback(new MyConfirmCallback());
        template.setReturnCallback(new MyCallBack());

        CorrelationData correlationData = new CorrelationData("oderId 1213");
        template.convertAndSend("directExchange", "direct.key123", message, correlationData);
    }

    public void sendMessage() {
        CorrelationData correlationData = new CorrelationData("oderId 1213");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "gary");
        map.put("password", "123");
        template.setMessageConverter(new MyMessageConverter());

//        template.convertAndSend("directExchange", "direct.key", JSON.toJSONString(map), correlationData);
//      用消息解析器来处理
        template.convertAndSend("directExchange", "direct.key", map, correlationData);

    }








}
