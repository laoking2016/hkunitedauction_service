package com.hkunitedauction.mall.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.*;
import com.hkunitedauction.mall.service.CartService;
import com.hkunitedauction.mall.service.GoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Assert;

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

        Example example = new Example(CartPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buyer", currentUser);

        QueryResult<Cart> result = this.service.query(example, new RowBounds(0, 1));
        if(result.getTotalCount() == 0){
            return null;
        }

        return result.getList()[0];
    }

    @ApiOperation(value = "create")
    @Override
    public void create(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId) {
        Example exampleCart = new Example(CartPO.class);
        Example.Criteria criteriaCart = exampleCart.createCriteria();
        criteriaCart.andEqualTo("buyer", currentUser);
        QueryResult<Cart> resultCart = this.service.query(exampleCart, new RowBounds(0, 1));
        if(resultCart.getTotalCount() == 0){
            Cart model = Cart.build(currentUser, goodId);
            this.service.create(model);
        }else{
            Cart model = resultCart.getList()[0];
            model.createItem(goodId);
            this.service.update(model.getId(), model);
        }
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@RequestParam("currentUser") String currentUser, @RequestParam("ids") Long[] ids) {
        Example exampleCart = new Example(CartPO.class);
        Example.Criteria criteriaCart = exampleCart.createCriteria();
        criteriaCart.andEqualTo("buyer", currentUser);
        QueryResult<Cart> resultCart = this.service.query(exampleCart, new RowBounds(0, 1));
        Assert.isTrue(resultCart.getTotalCount() > 0, "购物车不存在");

        Cart model = resultCart.getList()[0];
        model.deleteItem(ids);
        this.service.update(model.getId(), model);
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId, @RequestParam("quantity") Integer quantity) {
        Example exampleCart = new Example(CartPO.class);
        Example.Criteria criteriaCart = exampleCart.createCriteria();
        criteriaCart.andEqualTo("buyer", currentUser);
        QueryResult<Cart> resultCart = this.service.query(exampleCart, new RowBounds(0, 1));
        if(resultCart.getTotalCount() == 0){
            Cart model = Cart.build(currentUser, goodId);
            model.updateItem(goodId, quantity);
            this.service.create(model);
        }else{
            Cart model = resultCart.getList()[0];
            model.updateItem(goodId, quantity);
            this.service.update(model.getId(), model);
        }
    }
}
