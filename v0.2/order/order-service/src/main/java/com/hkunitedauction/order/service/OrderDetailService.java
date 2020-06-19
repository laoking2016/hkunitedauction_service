package com.hkunitedauction.order.service;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.order.model.OrderDetail;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

public interface OrderDetailService {

    int count(Example example);
    QueryResult<OrderDetail> query(Example example, RowBounds rowBounds);
    Long create(OrderDetail model);
    void delete(Long id);
    void patch(Long id, OrderDetail model);
}
