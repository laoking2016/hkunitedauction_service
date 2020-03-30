package com.hkunitedauction.auction.api;


import com.hkunitedauction.auction.model.*;
import com.hkunitedauction.auction.service.BidService;
import com.hkunitedauction.auction.service.LotService;
import com.hkunitedauction.auction.service.MemberService;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.member.model.Member;
import com.hkunitedauction.util.QueryBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Assert;

import java.util.Arrays;
import java.util.Date;

@Api(value = "Bid")
@RestController
@RequiredArgsConstructor
public class BidFacadeImpl implements BidFacade {

    @Autowired
    private BidService service;

    @Autowired
    private LotService lotService;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "query")
    @Override
    public QueryResult<Bid> query(@RequestParam(value = "filter", required = false) String filter,
                                  @RequestParam(value = "sort", required = false) String sort,
                                  @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                  @RequestParam(value = "page", required = false) Integer page) {


        Example example = new Example(BidPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        return this.service.query(example, rowBounds);
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestParam("currentUser") String currentUser, @RequestBody Bid model) {
        Date now = new Date();

        //获取竞买
        Example exampleLot = new Example(LotPO.class);
        Example.Criteria criteriaLot = exampleLot.createCriteria();
        criteriaLot.andEqualTo("id", model.getParentId());
        criteriaLot.andEqualTo("status", LotStatus.ONSALE.getValue());
        QueryResult<Lot> resultLot = this.lotService.query(exampleLot, new RowBounds(0, 1));
        Assert.isTrue(resultLot.getTotalCount() > 0, "竞买不存在");
        Lot lot = resultLot.getList()[0];
        //验证出价
        lot.validate(model);
        //获取会员信息
        QueryResult<Member> resultMember =
                this.memberService.query(String.format("name=%s", currentUser), null, null, null);
        Assert.isTrue(resultMember.getTotalCount() > 0, "会员账号不存在");
        Member member = resultMember.getList()[0];
        //保存出价信息
        model.setBidderName(currentUser);
        model.setBidTime(now);
        Long id = this.service.create(model);
        //更新竞买信息
        Example exampleBid = new Example(BidPO.class);
        Example.Criteria criteriaBid = exampleBid.createCriteria();
        criteriaBid.andEqualTo("parentId", model.getParentId());
        exampleBid.orderBy("bidPrice").desc();
        QueryResult<Bid> resultBid = this.service.query(exampleBid, new RowBounds(0, 3));
        lot.bid(Arrays.asList(resultBid.getList()));
        lot.setWinnerName(currentUser);
        lot.setBidTime(now);
        lot.setWinnerTrueName(member.getTrueName());
        lot.setWinnerContact(member.getContact());
        lot.setWinnerWechat(member.getWechat());
        lot.setWinnerAddress(member.getAddress());
        this.lotService.update(lot.getId(), lot);

        return id;
    }
}
