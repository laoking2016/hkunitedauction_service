package com.hkunitedauction.auction.api;

import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.common.response.QueryResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/lot")
public interface LotFacade {

    @GetMapping("/count")
    int count(@RequestParam(value = "filter", required = false) String filter);

    @GetMapping
    QueryResult<Lot> query(@RequestParam(value = "filter", required = false) String filter,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(value = "pagasize", required = false) Integer pagesize,
                           @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestBody Lot model);

    @PutMapping("/{id}")
    void update(@PathVariable("id") Long id, @RequestBody Lot model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    void patch(@PathVariable("id") Long id, @RequestBody Lot model);

    @PostMapping("/{id}/publish")
    void publish(@PathVariable("id") Long id);

    @PostMapping("/{id}/unpublish")
    void unpublish(@PathVariable("id") Long id);


}
