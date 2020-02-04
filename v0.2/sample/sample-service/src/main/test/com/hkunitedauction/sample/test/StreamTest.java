package com.hkunitedauction.sample.test;

import com.hkunitedauction.sample.SampleServiceApplication;
import com.hkunitedauction.sample.service.ISendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleServiceApplication.class)
public class StreamTest {

    @Autowired
    private ISendService send;

    @Test
    public void send() throws InterruptedException {
        String msg = "sample.............";
        Message message = MessageBuilder.withPayload(msg.getBytes()).build();
        this.send.send().send(message);
    }
}
