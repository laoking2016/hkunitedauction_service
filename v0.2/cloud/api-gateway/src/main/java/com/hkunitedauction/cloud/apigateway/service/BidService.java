package com.hkunitedauction.cloud.apigateway.service;

import com.hkunitedauction.auction.api.BidFacade;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(name="auction")
public interface BidService extends BidFacade {
}
