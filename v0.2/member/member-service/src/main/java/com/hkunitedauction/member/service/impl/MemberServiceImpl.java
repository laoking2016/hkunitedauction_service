package com.hkunitedauction.member.service.impl;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.member.mapper.MemberPOMapper;
import com.hkunitedauction.member.model.Login;
import com.hkunitedauction.member.model.Member;
import com.hkunitedauction.member.model.MemberPO;
import com.hkunitedauction.member.service.MemberService;
import com.netflix.discovery.converters.Auto;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;



    @Override
    public int count(Example example){
        return this.poMapper.selectCountByExample(example);
    }

    @Override
    public QueryResult<Member> query(Example example, RowBounds rowBounds) {
        Member[] list = this.poMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    Member model = this.dozerMapper.map(e, Member.class);
                    return model;
                }).collect(Collectors.toList()).toArray(new Member[0]);

        int totalCount = this.poMapper.selectCountByExample(example);
        QueryResult<Member> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @Override
    public Long create(Member model) {
        MemberPO po = this.dozerMapper.map(model, MemberPO.class);
        po.setId(null);
        po.setDeleted(false);
        Date now = new Date();
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.poMapper.insert(po);
        return po.getId();
    }

    @Override
    public void update(Long id, Member model) {
        MemberPO po = this.poMapper.selectByPrimaryKey(id);

        if(po != null){
            po.setName(model.getName());
            po.setTrueName(model.getTrueName());
            po.setContact(model.getContact());
            po.setAddress(model.getAddress());
            po.setWechat(model.getWechat());
            po.setEmail(model.getEmail());
            po.setModifedTime(new Date());
            this.poMapper.updateByPrimaryKey(po);
        }
    }

    @Override
    public void delete(Long id) {
        MemberPO po = new MemberPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.poMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void patch(Long id, Member model) {
        MemberPO po = this.dozerMapper.map(model, MemberPO.class);
        po.setId(model.getId());
        this.poMapper.updateByPrimaryKeySelective(po);
    }
}
