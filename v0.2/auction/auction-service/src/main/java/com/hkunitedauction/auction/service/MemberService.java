package com.hkunitedauction.auction.service;

import com.hkunitedauction.member.api.MemberFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("member")
public interface MemberService extends MemberFacade {
}
