package com.mimi.consumer;

import com.mimi.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class FanoutConsumer1 {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
//        声明队列  队列也可以在客户声明
        channel.queueDeclare("queue1", true, false, false, null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println(new String(body, "utf-8"));



            }
        };

        channel.basicConsume("queue1",true,defaultConsumer);





    }
}
