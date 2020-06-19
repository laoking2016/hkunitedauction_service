package com.hkunitedauction.order.service;

import com.hkunitedauction.auction.api.LotFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("auction")
public interface LotService extends LotFacade {
}
