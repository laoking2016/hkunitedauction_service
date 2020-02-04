package com.hkunitedauction.sample.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface ISendService {

    @Output("sample-exchange")
    SubscribableChannel send();
}
