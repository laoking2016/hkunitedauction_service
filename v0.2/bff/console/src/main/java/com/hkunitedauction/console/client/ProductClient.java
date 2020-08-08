package com.hkunitedauction.console.client;

import com.hkunitedauction.maindata.api.ProductFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="product", url = "${feign.product.url:}")
public interface ProductClient extends ProductFacade {

}
