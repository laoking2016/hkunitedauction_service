package com.hkunitedauction.console.api.maindata;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.ProductClient;
import com.hkunitedauction.maindata.api.ProductFacade;
import com.hkunitedauction.maindata.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Product")
@RestController
@RequestMapping("/main-data/product")
public class ProductController implements ProductFacade {

    @Autowired
    private ProductClient client;

    @ApiOperation(value = "count")
    @GetMapping("/count")
    public int count(@RequestParam(value = "filter", required = false) String filter){
        return client.count(filter);
    }

    @ApiOperation(value = "query")
    @GetMapping
    public QueryResult<Product> query(@RequestParam(value = "filter", required = false) String filter,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "pagasize", required = false) Integer pagesize,
                               @RequestParam(value = "page", required = false) Integer page){

        return client.query(filter, sort, pagesize, page);
    }

    @ApiOperation(value = "create")
    @PostMapping
    public Long create(@RequestBody Product model){
        return client.create(model);
    }

    @ApiOperation(value = "update")
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Product model){
        client.update(id, model);
    }

    @ApiOperation(value = "delete")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        client.delete(id);
    }
}
