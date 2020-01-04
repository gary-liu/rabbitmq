package com.mimi.mq.callback;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * create by gary 2020/1/4
 * 技术交流请加QQ:498982703
 */
public class MyMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        messageProperties.setContentType("text/xml");
        messageProperties.setContentEncoding("UTF-8");
        Message message = new Message(JSON.toJSONBytes(object), messageProperties);
        System.out.println("调用了消息解析器");
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return null;
    }
}
