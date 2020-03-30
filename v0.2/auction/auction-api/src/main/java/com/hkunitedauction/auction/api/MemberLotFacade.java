package com.hkunitedauction.auction.api;

import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.common.response.QueryResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/member/lot")
public interface MemberLotFacade {

    @GetMapping("/getting")
    QueryResult<Lot> queryGetting(@RequestParam("currentUser") String currentUser,
                                  @RequestParam("page") Integer page,
                                  @RequestParam("pagesize") Integer pagesize);

    @GetMapping("/got")
    QueryResult<Lot> queryGot(@RequestParam("currentUser") String currentUser,
                              @RequestParam("page") Integer page,
                              @RequestParam("pagesize") Integer pagesize);

    @GetMapping("/failed")
    QueryResult<Lot> queryFailed(@RequestParam("currentUser") String currentUser,
                                 @RequestParam("page") Integer page,
                                 @RequestParam("pagesize") Integer pagesize);
}
