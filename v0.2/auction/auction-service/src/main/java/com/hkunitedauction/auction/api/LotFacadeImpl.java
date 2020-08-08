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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Assert;

import java.util.Arrays;
import java.util.stream.Collectors;

@Api(value = "Lot")
@RestController
@RequiredArgsConstructor
public class LotFacadeImpl implements LotFacade {

    @Autowired
    private LotService service;

    @Autowired
    private BidService bidService;

    @Override
    @ApiOperation(value = "count")
    public int count(@RequestParam(value = "filter", required = false) String filter) {

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        return this.service.count(example);
    }

    @ApiOperation(value = "query")
    @Override
    public QueryResult<Lot> query(@RequestParam(value = "filter", required = false) String filter,
                                  @RequestParam(value = "sort", required = false) String sort,
                                  @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                  @RequestParam(value = "page", required = false) Integer page) {

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        return this.service.query(example, rowBounds);
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestBody Lot model) {
        model.validate();
        return this.service.create(model);
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@PathVariable Long id, @RequestBody Lot model) {
        model.validate();
        this.service.update(id, model);
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

    @ApiOperation(value = "patch")
    @Override
    public void patch(@PathVariable Long id, @RequestBody Lot model){
        this.service.patch(id, model);
    }

    @ApiOperation(value = "publish")
    @Override
    public void publish(@PathVariable Long id) {

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        QueryResult<Lot> result = this.service.query(example, new RowBounds(0, 1));

        Assert.isTrue(result.getTotalCount() == 1, "数据不存在");

        Lot model = result.getList()[0];
        model.publish();
        this.service.update(id, model);
    }

    @ApiOperation(value = "unpublish")
    @Override
    public void unpublish(@PathVariable Long id) {

        Lot value = new Lot();
        value.setId(id);
        value.setStatus(LotStatus.DRAFT);

        this.service.patch(id, value);
    }

    @ApiOperation(value = "search count")
    @Override
    public int searchCount(@RequestParam(value = "q", required = false) String q,
                           @RequestParam(value = "catalog", required = false) String catalog){
        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("l1", catalog);
        criteria.andLike("description", "%" + q + "%");

        return this.service.count(example);
    }

    @ApiOperation(value = "search")
    @Override
    public QueryResult<Lot> search(@RequestParam(value = "q", required = false) String q,
                                    @RequestParam(value = "catalog", required = false) String catalog,
                                    @RequestParam(value = "sort", required = false) String sort,
                                    @RequestParam(value = "pagesize", required = false) Integer pagesize,
                                    @RequestParam(value = "page", required = false) Integer page){

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("l1", catalog);
        criteria.andLike("description", "%" + q + "%");

        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        return this.service.query(example, rowBounds);
    }

    @ApiOperation(value = "Proceeding")
    @Override
    public QueryResult<Lot> proceedingQuery(
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

        return this.service.query(example, QueryBuilder.buildRowBounds(page, pagesize));
    }

    @ApiOperation(value = "Win")
    @Override
    public QueryResult<Lot> winQuery(@RequestParam("currentUser") String currentUser,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("pagesize") Integer pagesize) {

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", LotStatus.CLOSED.getValue());
        criteria.andEqualTo("winnerName", currentUser);
        example.orderBy("bidTime").desc();

        return this.service.query(example, QueryBuilder.buildRowBounds(page, pagesize));
    }

    @ApiOperation(value = "lost")
    @Override
    public QueryResult<Lot> lostQuery(@RequestParam("currentUser") String currentUser,
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

        return this.service.query(example, QueryBuilder.buildRowBounds(page, pagesize));
    }
}
