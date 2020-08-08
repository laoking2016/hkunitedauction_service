package com.hkunitedauction.console.api.auction;

import com.hkunitedauction.auction.api.BidFacade;
import com.hkunitedauction.auction.model.Bid;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.BidClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Bid")
@RestController
@RequestMapping("/auction/bid")
public class BidController implements BidFacade {

    @Autowired
    private BidClient client;

    @GetMapping
    @ApiOperation("query")
    public QueryResult<Bid> query(@RequestParam(value = "filter", required = false) String filter,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(value = "pagasize", required = false) Integer pagesize,
                           @RequestParam(value = "page", required = false) Integer page){
        return client.query(filter, sort, pagesize, page);
    }

    @PostMapping
    @ApiOperation("create")
    public Long create(@RequestParam("currentUser") String currentUser, @RequestBody Bid model){
        return client.create(currentUser, model);
    }
}
