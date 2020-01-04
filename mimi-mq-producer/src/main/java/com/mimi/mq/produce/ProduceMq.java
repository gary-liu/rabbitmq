package com.mimi.mq.produce;

import com.mimi.mq.callback.MyConfirmCallback;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProduceMq {

    @Autowired
    RabbitTemplate template;

    public void sendMessage(String message) {
//        template.convertAndSend(message);
//        template.setReturnCallback(new MyCallBack());

        template.convertAndSend("directExchange", "direct.key",message);

    }

    public void sendConfirmMessage(String message) {
        template.setConfirmCallback(new MyConfirmCallback());
        CorrelationData correlationData = new CorrelationData("oderId 1213");
        template.convertAndSend("directExchange", "direct.key", message, correlationData);
    }








}
