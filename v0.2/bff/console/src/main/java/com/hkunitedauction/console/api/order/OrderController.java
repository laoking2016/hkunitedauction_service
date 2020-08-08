package com.hkunitedauction.console.api.order;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.OrderClient;
import com.hkunitedauction.order.api.OrderFacade;
import com.hkunitedauction.order.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Order")
@RestController
@RequestMapping("/order/order")
public class OrderController implements OrderFacade {

    @Autowired
    private OrderClient client;

    @GetMapping("/count")
    @ApiOperation("count")
    public int count(String filter) {
        return client.count(filter);
    }

    @GetMapping
    @ApiOperation("Query")
    public QueryResult<Order> query(String filter, String sort, Integer pagesize, Integer page) {
        return client.query(filter, sort, pagesize, page);
    }

    @PostMapping
    @ApiOperation("create")
    public Long create(String currentUser, Order model) {
        return client.create(currentUser, model);
    }

    @DeleteMapping
    @ApiOperation("delete")
    public void delete(Long id) {
        client.delete(id);
    }

    @DeleteMapping
    @ApiOperation("patch")
    public void patch(Long id, Order model) {
        client.patch(id, model);
    }

    @PostMapping("/{id}/pay")
    public void pay(@PathVariable Long id){
        client.pay(id);
    }

    @PostMapping("/{id}/ship")
    public void ship(@PathVariable("id") Long id, @RequestParam("shipNo") String shipNo){
        client.ship(id, shipNo);
    }

    @PostMapping("/{id}/receive")
    public void receive(@PathVariable("id") Long id){
        client.receive(id);
    }

    @PostMapping("/{id}/close")
    public void close(@PathVariable("id") Long id){
        client.close(id);
    }
}
