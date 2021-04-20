package com.hkunitedauction.mall.service;


import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.Cart;

public interface CartService {

    //int count(String user);
    QueryResult<Cart> query(String user);
    void create(String user, Long goodId);
    void update(String user, Long goodId, Integer quantity);
    void delete(String user, Long[] goodIds);
    //void patch(Long id, Cart model);
}
