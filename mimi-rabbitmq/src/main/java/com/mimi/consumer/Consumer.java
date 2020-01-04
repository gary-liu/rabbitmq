package com.mimi.consumer;

import com.mimi.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println(new String(body, "utf-8"));
                System.out.println("consumerTag:"+consumerTag);
                System.out.println("Envelop:"+envelope.toString());
                System.out.println("AMQP.BasicProperties:" + properties.toString());

            }
        };
        channel.basicConsume(ConnectionUtil.QUEUE_NAME, defaultConsumer);





    }
}
