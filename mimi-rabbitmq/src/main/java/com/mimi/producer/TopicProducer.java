package com.mimi.producer;

import com.mimi.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class TopicProducer {
    public static void main(String[] args) throws Exception {
        String mesage = "hello";
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /*声明exchange*/
        channel.exchangeDeclare("topicExchange", BuiltinExchangeType.TOPIC,true);
        /*交换机和队列绑定*/
//        channel.queueBind("topicQueue1", "topicExchange", "user.*");
        channel.queueBind("topicQueue1", "topicExchange", "#");
        channel.queueBind("topicQueue2", "topicExchange", "user.error");

        channel.basicPublish("topicExchange", "user.error", null, mesage.getBytes());
        System.out.println("发送的信息为:" + mesage);
        channel.close();
        connection.close();





    }


}
