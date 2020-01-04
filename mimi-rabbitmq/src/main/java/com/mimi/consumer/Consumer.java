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
                System.out.println("消息消费成功==========");
//                手动确认消息消费  如果客户端异常断开连接，消息会自动恢复到ready状态
                channel.basicAck(envelope.getDeliveryTag(), false);
                /*消息重复消费的问题rabbitmq没有给我们解决方案
                但业务中我们可以自已定义给这个消息加一个状态status 如
                 * 果消费过就是置为1 没有消费过就是置为0(借用业务状态标识下)  */


            }
        };
        channel.basicConsume(ConnectionUtil.QUEUE_NAME, defaultConsumer);
        /*是否自己动确认消息消费 默认false  如果消息对业务没有特别的影响可以采用自动确认*/
//        channel.basicConsume(ConnectionUtil.QUEUE_NAME,true,defaultConsumer);





    }
}
