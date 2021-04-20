package com.hkunitedauction.mobile.api.mall;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.api.GoodFacade;
import com.hkunitedauction.mall.model.Good;
import com.hkunitedauction.mobile.client.GoodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Good")
@RestController
@RequestMapping("/mall/good")
public class GoodController implements GoodFacade {

    @Autowired
    private GoodClient client;

    @GetMapping("/count")
    @ApiOperation("count")
    public int count(@RequestParam(value = "filter", required = false) String filter){
        return client.count(filter);
    }

    @GetMapping
    @ApiOperation("query")
    public QueryResult<Good> query(@RequestParam(value = "filter", required = false) String filter,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                   @RequestParam(value = "page", required = false) Integer page){
        return client.query(filter, sort, pagesize, page);
    }

    @PostMapping
    @ApiOperation("create")
    public Long create(@RequestBody Good model){
        return client.create(model);
    }

    @PutMapping("/{id}")
    @ApiOperation("update")
    public void update(@PathVariable("id") Long id, @RequestBody Good model){
        client.update(id, model);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete")
    public void delete(@PathVariable("id") Long id){
        client.delete(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("patch")
    public void patch(@PathVariable("id") Long id, @RequestBody Good model){
        client.patch(id, model);
    }

    @GetMapping("/search/count")
    @ApiOperation("search count")
    public int searchCount(@RequestParam(value = "q", required = false) String q,
                           @RequestParam(value = "catalog", required = false) String catalog){
        return client.searchCount(q, catalog);
    }

    @GetMapping("search")
    @ApiOperation("search")
    public QueryResult<Good> search(@RequestParam(value = "q", required = false) String q,
                                    @RequestParam(value = "catalog", required = false) String catalog,
                                    @RequestParam(value = "sort", required = false) String sort,
                                    @RequestParam(value = "pagesize", required = false) Integer pagesize,
                                    @RequestParam(value = "page", required = false) Integer page){
        return client.search(q, catalog, sort, pagesize, page);
    }
}