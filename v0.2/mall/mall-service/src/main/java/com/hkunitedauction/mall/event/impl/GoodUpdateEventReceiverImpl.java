package com.hkunitedauction.mall.event.impl;

import com.hkunitedauction.mall.event.GoodUpdateEvent;
import com.hkunitedauction.mall.event.GoodUpdateEventReceiver;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(bindings=@QueueBinding(
        value=@Queue(value="${mq.config.queue.es}", autoDelete="true"),
        exchange=@Exchange(value="${mq.config.exchange}", type= ExchangeTypes.FANOUT)
))
public class GoodUpdateEventReceiverImpl implements GoodUpdateEventReceiver {

    @Autowired
    private RestHighLevelClient client;

    @Override
    @RabbitHandler
    public void process(GoodUpdateEvent event){
        
        log.info(event.getName());
        return;
    }
}
