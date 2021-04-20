package com.hkunitedauction.order.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.order.model.Order;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrderFacade {

    @GetMapping("/count")
    int count(@RequestParam(value = "filter", required = false) String filter);

    @GetMapping
    QueryResult<Order> query(@RequestParam(value = "filter", required = false) String filter,
                            @RequestParam(value = "sort", required = false) String sort,
                            @RequestParam(value = "pagasize", required = false) Integer pagesize,
                            @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestParam("currentUser") String currentUser, @RequestBody Order model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    void patch(@PathVariable("id") Long id, @RequestBody Order model);

    @PostMapping("/{id}/pay")
    void pay(@PathVariable("id") Long id);

    @PostMapping("/{id}/ship")
    void ship(@PathVariable("id") Long id, @RequestParam("shipNo") String shipNo);

    @PostMapping("/{id}/receive")
    void receive(@PathVariable("id") Long id);

    @PostMapping("/{id}/close")
    void close(@PathVariable("id") Long id);
}
