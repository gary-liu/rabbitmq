package com.mimi.mq.controller;

import com.mimi.mq.produce.ProduceMq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by gary 2020/1/4
 * 技术交流请加QQ:498982703
 */
@RestController
public class OrderController {

    @Autowired
    private ProduceMq produceMq;

    @GetMapping("/order.do")
    public void order(String order) {
        System.out.println("下单成功");
        produceMq.sendMessage(order);
        System.out.println(order);

    }

    @GetMapping("confirmOrder.do")
    public void confirmOrder(String confirmOrder) {
        System.out.println("消息确认");
        produceMq.sendConfirmMessage(confirmOrder);
    }

    @GetMapping("confirmOrder2.do")
    public void confirmOrder() {
        System.out.println("消息确认2");
        produceMq.sendMessage();
    }

    @GetMapping("/manual")
    public void manual(String manual) {
        System.out.println("manual model");
        produceMq.sendManualMessage(manual);
    }
}
