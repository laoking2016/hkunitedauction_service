package com.hkunitedauction.maindata.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Image;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/image")
public interface ImageFacade {

    @GetMapping
    QueryResult<Image> query(@RequestParam(value = "filter", required = false) String filter,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "pagasize", required = false) Integer pagesize,
                             @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestParam("file") MultipartFile file);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
