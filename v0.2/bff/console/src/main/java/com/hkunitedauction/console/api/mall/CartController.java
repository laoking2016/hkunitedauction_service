package com.hkunitedauction.console.api.mall;

import com.hkunitedauction.console.client.CartClient;
import com.hkunitedauction.mall.api.CartFacade;
import com.hkunitedauction.mall.model.Cart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Cart")
@RestController
@RequestMapping("/mall/cart")
public class CartController implements CartFacade {

    @Autowired
    private CartClient client;

    @GetMapping
    @ApiOperation("query")
    public Cart query(@RequestParam("currentUser") String currentUser){
        return client.query(currentUser);
    }

    @PostMapping
    @ApiOperation("create")
    public void create(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId){
        client.create(currentUser, goodId);
    }

    @DeleteMapping
    @ApiOperation("delete")
    public void delete(@RequestParam("currentUser") String currentUser, @RequestParam("ids") Long[] ids){
        client.delete(currentUser, ids);
    }

    @PutMapping
    @ApiOperation("update")
    public void update(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId, @RequestParam("quantity") Integer quantity){
        client.update(currentUser, goodId, quantity);
    }
}
