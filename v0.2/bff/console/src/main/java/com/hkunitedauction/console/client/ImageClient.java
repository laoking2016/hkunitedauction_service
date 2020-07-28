package com.hkunitedauction.console.client;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Image;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(name="catalog", url = "${feign.image.url:}")
public interface ImageClient {

    @GetMapping
    QueryResult<Image> query(@RequestParam(value = "filter", required = false) String filter,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "pagasize", required = false) Integer pagesize,
                             @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestParam("file") MultipartFile file);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
