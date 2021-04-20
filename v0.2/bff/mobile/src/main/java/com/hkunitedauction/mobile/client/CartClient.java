package com.hkunitedauction.mobile.client;

import com.hkunitedauction.mall.api.CartFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="cart", url = "${feign.cart.url:}")
public interface CartClient extends CartFacade {
}