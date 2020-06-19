package com.hkunitedauction.order.service;

import com.hkunitedauction.mall.api.GoodFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("mall")
public interface GoodService extends GoodFacade {
}
