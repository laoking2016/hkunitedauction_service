package com.hkunitedauction.auction.service.impl;

import com.hkunitedauction.auction.mapper.BidPOMapper;
import com.hkunitedauction.auction.model.Bid;
import com.hkunitedauction.auction.model.BidPO;
import com.hkunitedauction.auction.service.BidService;
import com.hkunitedauction.common.response.QueryResult;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    private BidPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public Integer count(Example example) {
        return this.poMapper.selectCountByExample(example);
    }

    @Override
    public QueryResult<Bid> query(Example example, RowBounds rowBounds) {

        Bid[] list = this.poMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    Bid model = this.dozerMapper.map(e, Bid.class);
                    return model;
                }).collect(Collectors.toList()).toArray(new Bid[0]);

        int totalCount = this.poMapper.selectCountByExample(example);
        QueryResult<Bid> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @Override
    public Long create(Bid model){
        Date now = new Date();
        BidPO po = dozerMapper.map(model, BidPO.class);
        po.setId(null);
        po.setCreatedTime(now);
        po.setDeleted(false);
        po.setModifedTime(now);
        this.poMapper.insert(po);

        return po.getId();
    }
}
