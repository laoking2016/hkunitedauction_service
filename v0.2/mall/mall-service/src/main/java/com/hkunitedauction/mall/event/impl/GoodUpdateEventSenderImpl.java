package com.hkunitedauction.mall.event.impl;

import com.hkunitedauction.mall.event.GoodUpdateEvent;
import com.hkunitedauction.mall.event.GoodUpdateEventSender;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoodUpdateEventSenderImpl implements GoodUpdateEventSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    @Override
    public void send(GoodUpdateEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, "", event);
    }
}
