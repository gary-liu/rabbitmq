package com.mimi.producer;

import com.mimi.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class DirectProducer {
    public static void main(String[] args) throws Exception {
        String mesage = "hello";
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /*声明exchange*/
        channel.exchangeDeclare("directExchange", BuiltinExchangeType.DIRECT,true);
        /*交换机和队列绑定*/
        channel.queueBind("directQueue1", "directExchange", "info.user");
        channel.queueBind("directQueue2", "directExchange", "error.user");

        channel.basicPublish("directExchange", "info.user", null, mesage.getBytes());
        System.out.println("发送的信息为:" + mesage);
        channel.close();
        connection.close();





    }


}
