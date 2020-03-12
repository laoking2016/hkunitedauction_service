package com.hkunitedauction.maindata.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Product;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RequestMapping("/product")
public interface ProductFacade {

    @GetMapping
    QueryResult<Product> query(HttpServletRequest reqeust);

    @GetMapping("/{id}")
    Product get(@PathVariable Long id);

    @PostMapping
    Long create(@RequestBody Product model);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody Product model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
