package com.hkunitedauction.mall.service;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.model.Good;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

public interface GoodService {

    int count(Example example);
    QueryResult<Good> query(Example example, RowBounds rowBounds);
    Long create(Good model);
    void update(Long id, Good model);
    void delete(Long id);
    void patch(Long id, Good model);
}
