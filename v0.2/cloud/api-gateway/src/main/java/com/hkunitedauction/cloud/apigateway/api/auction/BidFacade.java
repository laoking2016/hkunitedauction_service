package com.hkunitedauction.cloud.apigateway.api.auction;

import com.hkunitedauction.auction.model.Bid;
import com.hkunitedauction.cloud.apigateway.service.BidService;
import com.hkunitedauction.cloud.apigateway.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BidFacade {

    @Autowired
    private BidService bidSevice;

    @Autowired
    private MemberService memberService;

    @PostMapping("/auction/bid")
    public Long create(@RequestHeader("Authorization") String token, @RequestBody Bid model){


        return new Long(1);
    }
}
