package com.hkunitedauction.mobile.api.mall;

import com.hkunitedauction.mall.api.CartFacade;
import com.hkunitedauction.mall.model.Cart;
import com.hkunitedauction.mobile.client.CartClient;
import com.hkunitedauction.mobile.service.JwtService;
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

    @Autowired
    private JwtService jwtService;

    @GetMapping
    @ApiOperation("query")
    public Cart query(@RequestHeader("Authorization") String token){
        String currentUser = jwtService.getUserNameFromToken(token);
        return client.query(currentUser);
    }

    @PostMapping
    @ApiOperation("create")
    public void create(@RequestHeader("Authorization") String token, @RequestParam("goodId") Long goodId){
        String currentUser = jwtService.getUserNameFromToken(token);
        client.create(currentUser, goodId);
    }

    @DeleteMapping
    @ApiOperation("delete")
    public void delete(@RequestHeader("Authorization") String token, @RequestParam("ids") Long[] ids){
        String currentUser = jwtService.getUserNameFromToken(token);
        client.delete(currentUser, ids);
    }

    @PutMapping
    @ApiOperation("update")
    public void update(@RequestHeader("Authorization") String token, @RequestParam("goodId") Long goodId, @RequestParam("quantity") Integer quantity){
        String currentUser = jwtService.getUserNameFromToken(token);
        client.update(currentUser, goodId, quantity);
    }
}
