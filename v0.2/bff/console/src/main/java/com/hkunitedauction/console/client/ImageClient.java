package com.hkunitedauction.console.client;

import com.hkunitedauction.maindata.api.ImageFacade;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(name="image", url = "${feign.image.url:}")
public interface ImageClient extends ImageFacade {
    
}
