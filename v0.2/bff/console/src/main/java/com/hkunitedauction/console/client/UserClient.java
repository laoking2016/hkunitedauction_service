package com.hkunitedauction.console.client;

import com.hkunitedauction.maindata.api.UserFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="user", url = "${feign.user.url:}")
public interface UserClient extends UserFacade {

}
