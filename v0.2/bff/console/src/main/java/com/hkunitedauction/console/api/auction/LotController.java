package com.hkunitedauction.console.api.auction;

import com.hkunitedauction.auction.api.LotFacade;
import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.LotClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Lot")
@RestController
@RequestMapping("/auction/lot")
public class LotController implements LotFacade {

    @Autowired
    private LotClient client;

    @GetMapping("/count")
    @ApiOperation("count")
    public int count(@RequestParam(value = "filter", required = false) String filter){
        return client.count(filter);
    }

    @GetMapping
    @ApiOperation("query")
    public QueryResult<Lot> query(@RequestParam(value = "filter", required = false) String filter,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(value = "pagasize", required = false) Integer pagesize,
                           @RequestParam(value = "page", required = false) Integer page){
        return client.query(filter, sort, pagesize, page);
    }

    @PostMapping
    @ApiOperation("create")
    public Long create(@RequestBody Lot model){
        return client.create(model);
    }

    @PutMapping("/{id}")
    @ApiOperation("update")
    public void update(@PathVariable("id") Long id, @RequestBody Lot model){
        client.update(id, model);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete")
    public void delete(@PathVariable("id") Long id){
        client.delete(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("patch")
    public void patch(@PathVariable("id") Long id, @RequestBody Lot model){
        client.patch(id, model);
    }

    @PostMapping("/{id}/publish")
    @ApiOperation("publish")
    public void publish(@PathVariable("id") Long id){
        client.publish(id);
    }

    @PostMapping("/{id}/unpublish")
    @ApiOperation("unpublish")
    public void unpublish(@PathVariable("id") Long id){
        client.unpublish(id);
    }

    @GetMapping("/search/count")
    @ApiOperation("search count")
    public int searchCount(@RequestParam(value = "q", required = false) String q,
                    @RequestParam(value = "catalog", required = false) String catalog){
        return client.searchCount(q, catalog);
    }

    @GetMapping("/search")
    @ApiOperation("search")
    public QueryResult<Lot> search(@RequestParam(value = "q", required = false) String q,
                            @RequestParam(value = "catalog", required = false) String catalog,
                            @RequestParam(value = "sort", required = false) String sort,
                            @RequestParam(value = "pagesize", required = false) Integer pagesize,
                            @RequestParam(value = "page", required = false) Integer page){
        return client.search(q, catalog, sort, pagesize, page);
    }

    @GetMapping("/proceeding")
    @ApiOperation("Proceeding")
    public QueryResult<Lot> proceedingQuery(String currentUser, Integer page, Integer pagesize) {
        return client.proceedingQuery(currentUser, page, pagesize);
    }

    @GetMapping("/win")
    @ApiOperation("Win")
    public QueryResult<Lot> winQuery(String currentUser, Integer page, Integer pagesize) {
        return client.winQuery(currentUser, page, pagesize);
    }

    @GetMapping("/lost")
    @ApiOperation("Lost")
    public QueryResult<Lot> lostQuery(String currentUser, Integer page, Integer pagesize) {
        return client.lostQuery(currentUser, page, pagesize);
    }
}
