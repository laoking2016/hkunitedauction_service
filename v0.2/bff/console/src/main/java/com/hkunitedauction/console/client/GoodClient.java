package com.hkunitedauction.console.client;

import com.hkunitedauction.mall.api.GoodFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="good", url = "${feign.good.url:}")
public interface GoodClient extends GoodFacade {
}
