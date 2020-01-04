package com.mimi.producer;

import com.mimi.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    public static void main(String[] args) throws Exception {
        String mesage = "hello";
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /*声明队列*/
        channel.queueDeclare(ConnectionUtil.QUEUE_NAME, true, false, false, null);
        /*声明exchange*/
        channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, "fanout");
        /*交换机和队列绑定*/
        channel.queueBind(ConnectionUtil.QUEUE_NAME, ConnectionUtil.EXCHANGE_NAME, "");

        channel.basicPublish(ConnectionUtil.EXCHANGE_NAME, "", null, mesage.getBytes());
        System.out.println("发送的信息为:" + mesage);
        channel.close();
        connection.close();





    }


}
