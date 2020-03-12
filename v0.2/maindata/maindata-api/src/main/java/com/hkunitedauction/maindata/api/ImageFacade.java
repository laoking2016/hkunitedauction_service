package com.hkunitedauction.maindata.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Image;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RequestMapping("/image")
public interface ImageFacade {

    @GetMapping
    QueryResult<Image> query(HttpServletRequest reqeust);

    @PostMapping
    Long create(@RequestParam("file") MultipartFile file);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
