package com.hkunitedauction.order.api;

import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.Good;
import com.hkunitedauction.member.model.Member;
import com.hkunitedauction.order.model.*;
import com.hkunitedauction.order.service.GoodService;
import com.hkunitedauction.order.service.LotService;
import com.hkunitedauction.order.service.MemberService;
import com.hkunitedauction.order.service.OrderService;
import com.hkunitedauction.util.QueryBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

@Api(value = "Order")
@RestController
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService service;

    @Autowired
    private MemberService memberService;

    @Autowired
    private LotService lotService;

    @Autowired
    private GoodService goodService;

    @ApiOperation(value = "count")
    @Override
    public int count(@RequestParam(value = "filter", required = false) String filter) {
        Example example = new Example(OrderPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        return this.service.count(example);
    }

    @ApiOperation(value = "query")
    @Override
    public QueryResult<Order> query(@RequestParam(value = "filter", required = false) String filter,
                                    @RequestParam(value = "sort", required = false) String sort,
                                    @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                    @RequestParam(value = "page", required = false) Integer page) {

        Example example = new Example(OrderPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        return this.service.query(example, rowBounds);
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestParam("currentUser") String currentUser, @RequestBody Order model) {

        QueryResult<Member> memberResult =
                this.memberService.query("name=" + currentUser, null, null, null);

        Member member = null;

        if(memberResult.getTotalCount() > 0){
            member = memberResult.getList()[0];
        }

        Good[] goods = null;
        Lot lot = null;

        StringBuffer buffer = new StringBuffer();
        for(OrderDetail item : model.getDetail()){
            if(buffer.length() == 0){
                buffer.append("id=" + item.getContextId());
            }else{
                buffer.append("&id=" + item.getContextId());
            }
        }
        String ids = buffer.toString();

        if(OrderType.MALL.equals(model.getType())){
            QueryResult<Good> goodsResult = this.goodService.query(ids, null, null, null);
            goods = goodsResult.getList();
        }else{
            QueryResult<Lot> lotResult = this.lotService.query(ids, null, null, null);
            if(lotResult.getTotalCount() > 0){
                lot = lotResult.getList()[0];
            }
        }

        if(OrderType.MALL.equals(model.getType())){
            model.createOrder(member, goods);
        }else{
            model.createOrder(member, lot);
        }

        return this.service.create(model);
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }

    @ApiOperation(value = "patch")
    @Override
    public void patch(@PathVariable("id") Long id, @RequestBody Order model) {
        this.service.patch(id, model);
    }

    @ApiOperation(value = "pay")
    @Override
    public void pay(@PathVariable("id") Long id) {
        Order model = new Order();
        model.setId(id);
        model.setStatus(OrderStatus.PAYED);
        this.service.patch(id, model);
    }

    @ApiOperation(value = "ship")
    @Override
    public void ship(@PathVariable("id") Long id, @RequestParam("shipNo") String shipNo) {
        Order model = new Order();
        model.setId(id);
        model.setStatus(OrderStatus.SHIPPING);
        this.service.patch(id, model);
    }

    @ApiOperation(value = "receive")
    @Override
    public void receive(@PathVariable("id") Long id) {
        Order model = new Order();
        model.setId(id);
        model.setStatus(OrderStatus.RECEIVED);
        this.service.patch(id, model);
    }

    @ApiOperation(value = "close")
    @Override
    public void close(@PathVariable("id") Long id) {
        Order model = new Order();
        model.setId(id);
        model.setStatus(OrderStatus.CLOSED);
        this.service.patch(id, model);
    }
}
