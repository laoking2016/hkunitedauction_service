package com.hkunitedauction.sample.service;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IReceiveService {

    @Input("sample-exchange")
    SubscribableChannel receive();
}
