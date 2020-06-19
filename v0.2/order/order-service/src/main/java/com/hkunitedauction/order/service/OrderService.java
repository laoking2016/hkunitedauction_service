package com.hkunitedauction.order.service;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.order.model.Order;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

public interface OrderService {

    int count(Example example);
    QueryResult<Order> query(Example example, RowBounds rowBounds);
    Long create(Order model);
    void delete(Long id);
    void patch(Long id, Order model);
}
