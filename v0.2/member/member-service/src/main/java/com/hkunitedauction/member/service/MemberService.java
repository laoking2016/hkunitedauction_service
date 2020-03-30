package com.hkunitedauction.member.service;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.member.model.Member;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

public interface MemberService {

    int count(Example example);
    QueryResult<Member> query(Example example, RowBounds rowBounds);
    Long create(Member model);
    void update(Long id, Member model);
    void delete(Long id);
    void patch(Long id, Member model);
}
