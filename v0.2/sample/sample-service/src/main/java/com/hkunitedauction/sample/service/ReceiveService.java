package com.hkunitedauction.sample.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding({IReceiveService.class})
public class ReceiveService {

    @StreamListener("sample-exchange")
    public void onReceive(byte[] msg){
        System.out.println("receive:" + new String(msg));
    }
}
