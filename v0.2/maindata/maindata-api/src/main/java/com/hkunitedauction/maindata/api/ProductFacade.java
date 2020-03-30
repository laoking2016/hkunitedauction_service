package com.hkunitedauction.maindata.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Product;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/product")
public interface ProductFacade {

    @GetMapping
    QueryResult<Product> query(@RequestParam(value = "filter", required = false) String filter,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "pagasize", required = false) Integer pagesize,
                               @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestBody Product model);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody Product model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
