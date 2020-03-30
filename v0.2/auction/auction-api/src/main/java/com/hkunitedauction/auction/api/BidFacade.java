package com.hkunitedauction.auction.api;

import com.hkunitedauction.auction.model.Bid;
import com.hkunitedauction.common.response.QueryResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bid")
public interface BidFacade {

    @GetMapping
    QueryResult<Bid> query(@RequestParam(value = "filter", required = false) String filter,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(value = "pagasize", required = false) Integer pagesize,
                           @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestParam("currentUser") String currentUser, @RequestBody Bid model);
}
