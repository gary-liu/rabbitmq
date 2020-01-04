package com.mimi.producer;

import com.mimi.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class FanoutProducer {
    public static void main(String[] args) throws Exception {
        String mesage = "hello";
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /*声明exchange*/
        channel.exchangeDeclare("fanoutExchange", BuiltinExchangeType.FANOUT);
        /*交换机和队列绑定*/
        channel.queueBind("queue1", "fanoutExchange", "");
        channel.queueBind("queue2", "fanoutExchange", "");

        channel.basicPublish("fanoutExchange", "", null, mesage.getBytes());
        System.out.println("发送的信息为:" + mesage);
        channel.close();
        connection.close();





    }


}
