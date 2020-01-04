package com.mimi.mq.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * create by gary 2020/1/4
 * 技术交流请加QQ:498982703
 */
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("CorrelateionData:"+correlationData.toString());
        System.out.println(ack);
        System.out.println("cause:" + cause);
    }
}
