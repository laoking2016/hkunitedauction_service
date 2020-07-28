package com.hkunitedauction.console.api.maindata;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.ImageClient;
import com.hkunitedauction.maindata.model.Image;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "Image")
@RestController
@RequestMapping("/main-data/image")
public class ImageController {

    @Autowired
    private ImageClient client;

    @ApiOperation("query")
    @GetMapping
    public QueryResult<Image> query(@RequestParam(value = "filter", required = false) String filter,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "pagasize", required = false) Integer pagesize,
                             @RequestParam(value = "page", required = false) Integer page){
        return client.query(filter, sort, pagesize, page);
    }

    @ApiOperation("create")
    @PostMapping
    public Long create(@RequestParam("file") MultipartFile file){
        return client.create(file);
    }

    @ApiOperation("delete")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        client.delete(id);
    }
}
