package com.mimi.mq;

import com.mimi.mq.produce.ProduceMq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MimiProducerMqApplicationTests {

    @Autowired
    ProduceMq produceMq;



    @Test
    public void contextLoads() {
        System.out.println("hello");

    }






}
