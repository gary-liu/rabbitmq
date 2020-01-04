package com.mimi.mq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class Consumer {

    /*监听队列*/
    @RabbitListener(queues={"testQueue"})
    public void get(String message) {
        System.out.println(message);

    }

    @RabbitListener(queues={"testQueue"})
    public void getMessage(Message message) throws UnsupportedEncodingException {
        System.out.println(new String(message.getBody(),"utf-8") );
    }


}
