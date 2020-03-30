package com.hkunitedauction.auction.api;

import com.hkunitedauction.auction.model.*;
import com.hkunitedauction.auction.service.BidService;
import com.hkunitedauction.auction.service.LotService;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.util.QueryBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.stream.Collectors;

@Api(value = "MemmberLot")
@RestController
@RequiredArgsConstructor
public class MemberLotFacadeImpl implements MemberLotFacade {

    @Autowired
    private BidService bidService;

    @Autowired
    private LotService lotService;

    @ApiOperation(value = "getting")
    @Override
    public QueryResult<Lot> queryGetting(
            @RequestParam("currentUser") String currentUser,
            @RequestParam("page") Integer page,
            @RequestParam("pagesize") Integer pagesize){

        Example exampleBid = new Example(BidPO.class);
        Example.Criteria criteriaBid = exampleBid.createCriteria();
        criteriaBid.andEqualTo("bidderName", currentUser);
        QueryResult<Bid> resultBid = this.bidService.query(exampleBid, new RowBounds(0, 100000));

        if(resultBid.getTotalCount() == 0){
            return new QueryResult<>();
        }
        
        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", LotStatus.ONSALE.getValue());
        criteria.andIn("id",
                Arrays.stream(resultBid.getList()).map(e -> e.getParentId()).distinct().collect(Collectors.toList()));
        example.orderBy("bidTime").desc();

        return this.lotService.query(example, QueryBuilder.buildRowBounds(page, pagesize));
    }

    @ApiOperation(value = "got")
    @Override
    public QueryResult<Lot> queryGot(@RequestParam("currentUser") String currentUser,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("pagesize") Integer pagesize) {

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", LotStatus.CLOSED.getValue());
        criteria.andEqualTo("winnerName", currentUser);
        example.orderBy("bidTime").desc();

        return this.lotService.query(example, QueryBuilder.buildRowBounds(page, pagesize));
    }

    @ApiOperation(value = "failed")
    @Override
    public QueryResult<Lot> queryFailed(@RequestParam("currentUser") String currentUser,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("pagesize") Integer pagesize) {

        Example exampleBid = new Example(BidPO.class);
        Example.Criteria criteriaBid = exampleBid.createCriteria();
        criteriaBid.andEqualTo("bidderName", currentUser);
        QueryResult<Bid> resultBid = this.bidService.query(exampleBid, new RowBounds(0, 100000));

        if(resultBid.getTotalCount() == 0){
            return new QueryResult<>();
        }

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", LotStatus.CLOSED.getValue());
        criteria.andNotEqualTo("winnerName", currentUser);
        criteria.andIn("id",
                Arrays.stream(resultBid.getList()).map(e -> e.getParentId()).distinct().collect(Collectors.toList()));

        return this.lotService.query(example, QueryBuilder.buildRowBounds(page, pagesize));
    }
}
