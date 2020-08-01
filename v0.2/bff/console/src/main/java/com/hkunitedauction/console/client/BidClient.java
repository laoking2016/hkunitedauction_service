package com.hkunitedauction.console.client;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="bid", url = "${feign.bid.url:}")
public interface BidClient {

}
