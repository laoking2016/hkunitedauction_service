package com.hkunitedauction.mall.service;


import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.Cart;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

public interface CartService {

    int count(Example example);
    QueryResult<Cart> query(Example example, RowBounds rowBounds);
    Long create(Cart model);
    void update(Long id, Cart model);
    void delete(Long id);
    void patch(Long id, Cart model);
}
