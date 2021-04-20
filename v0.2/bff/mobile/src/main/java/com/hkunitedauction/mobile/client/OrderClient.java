package com.hkunitedauction.mobile.client;

import com.hkunitedauction.order.api.OrderFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="order", url = "${feign.order.url:}")
public interface OrderClient extends OrderFacade {
}
