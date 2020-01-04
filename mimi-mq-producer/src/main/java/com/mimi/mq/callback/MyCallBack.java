package com.mimi.mq.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * create by gary 2020/1/4
 * 技术交流请加QQ:498982703
 */
@Component
public class MyCallBack implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
//        message ：发送的消息+发送消息的配制
        System.out.println("Message:"+message.toString());
        System.out.println("i:"+i);//返回的状态码
        System.out.println(s);//错误信息
        System.out.println(s1);//交换机
        System.out.println(s2);//路由键

    }
}
