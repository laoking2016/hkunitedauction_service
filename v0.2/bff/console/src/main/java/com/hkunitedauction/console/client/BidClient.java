package com.hkunitedauction.console.client;

import com.hkunitedauction.auction.api.BidFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="bid", url = "${feign.bid.url:}")
public interface BidClient extends BidFacade {

}
