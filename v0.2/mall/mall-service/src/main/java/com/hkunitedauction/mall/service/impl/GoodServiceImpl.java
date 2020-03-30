package com.hkunitedauction.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Image;
import com.hkunitedauction.mall.mapper.GoodPOMapper;
import com.hkunitedauction.mall.model.Good;
import com.hkunitedauction.mall.model.GoodPO;
import com.hkunitedauction.mall.model.GoodStatus;
import com.hkunitedauction.mall.service.GoodService;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public int count(Example example) {
        return this.poMapper.selectCountByExample(example);
    }

    @Override
    public QueryResult<Good> query(Example example, RowBounds rowBounds) {
        Good[] list = this.poMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    Good model = this.dozerMapper.map(e, Good.class);
                    model.setStatus(GoodStatus.valueOf(e.getStatus()));
                    if(e.getImages() != null){
                        model.setImages(JSON.parseObject(e.getImages(), Image[].class));
                    }
                    if(e.getPayments() != null)
                    {
                        model.setPayments(JSON.parseObject(e.getPayments(), String[].class));
                    }
                    return model;
                }).collect(Collectors.toList()).toArray(new Good[0]);

        int totalCount = this.poMapper.selectCountByExample(example);
        QueryResult<Good> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @Override
    public Long create(Good model) {
        GoodPO po = this.dozerMapper.map(model, GoodPO.class);
        po.setId(null);
        if(model.getImages() != null){
            po.setImages(JSON.toJSONString(model.getImages()));
        }
        if(model.getPayments() != null){
            po.setPayments(JSON.toJSONString(model.getPayments()));
        }
        po.setStatus(GoodStatus.DRAFT.getValue());
        po.setDeleted(false);
        Date now = new Date();
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.poMapper.insert(po);
        return po.getId();
    }

    @Override
    public void update(Long id, Good model) {
        GoodPO po = this.poMapper.selectByPrimaryKey(id);

        if(po != null){
            po.setName(model.getName());
            po.setDescription(model.getDescription());
            if(model.getImages() != null){
                po.setImages(JSON.toJSONString(model.getImages()));
            }
            if(model.getPayments() != null){
                po.setPayments(JSON.toJSONString(model.getPayments()));
            }
            po.setL1(model.getL1());
            po.setL2(model.getL2());
            po.setL3(model.getL3());
            po.setInitPrice(model.getInitPrice());
            po.setQuantity(model.getQuantity());
            po.setDiscount(model.getDiscount());
            po.setFinalPrice(model.getFinalPrice());
            po.setTransportationCost(model.getTransportationCost());
            po.setPoundageRate(model.getPoundageRate());
            po.setModifedTime(new Date());
            this.poMapper.updateByPrimaryKey(po);
        }
    }

    @Override
    public void delete(Long id) {
        GoodPO po = new GoodPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.poMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void patch(Long id, Good model) {
        GoodPO po = this.dozerMapper.map(model, GoodPO.class);
        po.setId(id);

        if(model.getStatus() != null){
            po.setStatus(model.getStatus().getValue());
        }

        if(model.getImages() != null){
            po.setImages(JSON.toJSONString(model.getImages()));
        }

        if(model.getPayments() != null){
            po.setPayments(JSON.toJSONString(model.getPayments()));
        }

        this.poMapper.updateByPrimaryKeySelective(po);
    }
}
