package com.mimi.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static final String QUEUE_NAME = "testQueue";
    public static final String EXCHANGE_NAME = "exchange";

    public static Connection getConnection() throws IOException, TimeoutException {

        /*创建一个连接工厂*/
        ConnectionFactory connectionFactory = new ConnectionFactory();
        /*设置rabbitmq服务端所在地址*/
        connectionFactory.setHost("127.0.0.1");
        /*设置端口号，连接用户名，虚拟地址*/
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("localtest");
        connectionFactory.setUsername("gary");
        connectionFactory.setPassword("123456");
        return connectionFactory.newConnection();

    }




}
