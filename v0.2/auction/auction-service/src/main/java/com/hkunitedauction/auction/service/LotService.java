package com.hkunitedauction.auction.service;

import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.common.response.QueryResult;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

public interface LotService {

    Lot get(Long id);
    int count(Example example);
    QueryResult<Lot> query(Example example, RowBounds rowBounds);
    Long create(Lot model);
    void update(Long id, Lot model);
    void delete(Long id);
    void patch(Long id, Lot model);
}
