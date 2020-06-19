package com.hkunitedauction.mall.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.Good;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/good")
public interface GoodFacade {

    @GetMapping("/count")
    int count(@RequestParam(value = "filter", required = false) String filter);

    @GetMapping
    QueryResult<Good> query(@RequestParam(value = "filter", required = false) String filter,
                            @RequestParam(value = "sort", required = false) String sort,
                            @RequestParam(value = "pagasize", required = false) Integer pagesize,
                            @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestBody Good model);

    @PutMapping("/{id}")
    void update(@PathVariable("id") Long id, @RequestBody Good model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    void patch(@PathVariable("id") Long id, @RequestBody Good model);

    @GetMapping("/search/count")
    int searchCount(@RequestParam(value = "q", required = false) String q,
                    @RequestParam(value = "sort", required = false) String sort,
                    @RequestParam(value = "pagasize", required = false) Integer pagesize,
                    @RequestParam(value = "page", required = false) Integer page);

    @GetMapping("search")
    QueryResult<Good> search(@RequestParam(value = "q", required = false) String q,
                    @RequestParam(value = "sort", required = false) String sort,
                    @RequestParam(value = "pagasize", required = false) Integer pagesize,
                    @RequestParam(value = "page", required = false) Integer page);
}
