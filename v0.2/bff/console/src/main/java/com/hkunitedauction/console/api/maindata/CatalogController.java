package com.hkunitedauction.console.api.maindata;

import com.hkunitedauction.console.client.CatalogClient;
import com.hkunitedauction.maindata.model.Catalog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Catalog")
@RestController
@RequestMapping("/main-data/catalog")
public class CatalogController {

    @Autowired
    private CatalogClient client;

    @ApiOperation("query")
    @GetMapping
    public Catalog query(){
        return client.query();
    }

    @ApiOperation("update")
    @PutMapping()
    public void update(@RequestBody Catalog model){
         client.update(model);
    }
}
