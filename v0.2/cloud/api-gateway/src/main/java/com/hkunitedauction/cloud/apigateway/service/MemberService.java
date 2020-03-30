package com.hkunitedauction.cloud.apigateway.service;

import com.hkunitedauction.member.api.MemberFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="member")
public interface MemberService extends MemberFacade {
}
