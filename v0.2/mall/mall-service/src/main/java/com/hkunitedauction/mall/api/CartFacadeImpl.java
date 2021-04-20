package com.hkunitedauction.mall.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.Cart;
import com.hkunitedauction.mall.service.CartService;
import com.hkunitedauction.mall.service.GoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Cart")
@RestController
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    @Autowired
    private CartService service;

    @Autowired
    private GoodService goodService;

    @ApiOperation(value = "query")
    @Override
    public Cart query(@RequestParam("currentUser") String currentUser) {

        QueryResult<Cart> result = this.service.query(currentUser);
        return result.getList()[0];
    }

    @ApiOperation(value = "create")
    @Override
    public void create(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId) {
        this.service.create(currentUser, goodId);
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@RequestParam("currentUser") String currentUser, @RequestParam("ids") Long[] ids) {
        this.service.delete(currentUser, ids);
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId, @RequestParam("quantity") Integer quantity) {
        this.service.update(currentUser, goodId, quantity);
    }
}
