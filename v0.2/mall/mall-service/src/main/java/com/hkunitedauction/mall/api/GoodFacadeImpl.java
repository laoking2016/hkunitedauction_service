package com.hkunitedauction.mall.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.Good;
import com.hkunitedauction.mall.model.GoodPO;
import com.hkunitedauction.mall.service.GoodService;
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

@Api(value = "Good")
@RestController
@RequiredArgsConstructor
public class GoodFacadeImpl implements GoodFacade {

    @Autowired
    private GoodService service;

    @ApiOperation(value = "count")
    @Override
    public int count(@RequestParam(value = "filter", required = false) String filter) {
        Example example = new Example(GoodPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        return this.service.count(example);
    }

    @ApiOperation(value = "query")  
    @Override
    public QueryResult<Good> query(@RequestParam(value = "filter", required = false) String filter,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                   @RequestParam(value = "page", required = false) Integer page) {

        Example example = new Example(GoodPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        return this.service.query(example, rowBounds);
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestBody Good model) {
        return this.service.create(model);
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@PathVariable Long id, @RequestBody Good model) {
        this.service.update(id, model);
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

    @ApiOperation(value = "patch")
    @Override
    public void patch(@PathVariable Long id, @RequestBody Good model) {
        this.service.patch(id, model);
    }

    @ApiOperation(value = "search count")
    @Override
    public int searchCount(@RequestParam(value = "q", required = false) String q,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(value = "pagasize", required = false) Integer pagesize,
                           @RequestParam(value = "page", required = false) Integer page){
        Example example = new Example(GoodPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("description", "%" + q + "%");

        this.service.count(example);
    }

    @ApiOperation(value = "search")
    @Override
    public QueryResult<Good> search(@RequestParam(value = "q", required = false) String q,
                                    @RequestParam(value = "sort", required = false) String sort,
                                    @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                    @RequestParam(value = "page", required = false) Integer page){

        Example example = new Example(GoodPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("description", "%" + q + "%");

        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        return this.service.query(example, rowBounds);
    }
}
