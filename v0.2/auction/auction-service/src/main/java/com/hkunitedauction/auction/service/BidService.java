package com.hkunitedauction.auction.service;

import com.hkunitedauction.auction.model.Bid;
import com.hkunitedauction.common.response.QueryResult;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

public interface BidService {

    Integer count(Example example);
    QueryResult<Bid> query(Example example, RowBounds rowBounds);
    Long create(Bid model);
}
