package com.hkunitedauction.console.client;

import com.hkunitedauction.member.api.MemberFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="member", url = "${feign.member.url:}")
public interface MemberClient extends MemberFacade {
}
