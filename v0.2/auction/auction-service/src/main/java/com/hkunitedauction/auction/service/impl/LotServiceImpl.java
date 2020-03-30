package com.hkunitedauction.auction.service.impl;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.auction.mapper.LotPOMapper;
import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.auction.model.LotPO;
import com.hkunitedauction.auction.model.LotStatus;
import com.hkunitedauction.auction.service.LotService;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Image;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class LotServiceImpl implements LotService {

    @Autowired
    private LotPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public Lot get(Long id){
        LotPO po = this.poMapper.selectByPrimaryKey(id);
        Lot model = this.dozerMapper.map(po, Lot.class);
        model.setStatus(LotStatus.valueOf(po.getStatus()));
        if(model.getImages() != null){
            model.setImages(JSON.parseObject(po.getImages(), Image[].class));
        }
        return model;
    }

    @Override
    public int count(Example example) {
        return this.poMapper.selectCountByExample(example);
    }

    @Override
    public QueryResult<Lot> query(Example example, RowBounds rowBounds) {

        Lot[] list = this.poMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    Lot model = this.dozerMapper.map(e, Lot.class);
                    model.setStatus(LotStatus.valueOf(e.getStatus()));
                    if(e.getImages() != null){
                        model.setImages(JSON.parseObject(e.getImages(), Image[].class));
                    }
                    if(e.getPayments() != null)
                    {
                        model.setPayments(JSON.parseObject(e.getPayments(), String[].class));
                    }
                    return model;
                }).collect(Collectors.toList()).toArray(new Lot[0]);

        int totalCount = this.poMapper.selectCountByExample(example);
        QueryResult<Lot> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @Override
    public Long create(Lot model) {
        LotPO po = this.dozerMapper.map(model, LotPO.class);
        po.setId(null);
        if(model.getImages() != null){
            po.setImages(JSON.toJSONString(model.getImages()));
        }
        if(model.getPayments() != null){
            po.setPayments(JSON.toJSONString(model.getPayments()));
        }
        po.setStatus(LotStatus.DRAFT.getValue());
        po.setDeleted(false);
        Date now = new Date();
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.poMapper.insert(po);
        return po.getId();
    }

    @Override
    public void update(Long id, Lot model) {
        LotPO po = this.poMapper.selectByPrimaryKey(id);

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
            po.setSupplierName(model.getSupplierName());
            po.setSupplierContact(model.getSupplierContact());
            po.setSupplierWechat(model.getSupplierWechat());
            po.setWinnerName(model.getWinnerName());
            po.setWinnerContact(model.getWinnerContact());
            po.setWinnerWechat(model.getWinnerWechat());
            po.setWinnerAddress(model.getWinnerAddress());
            po.setInitPrice(model.getInitPrice());
            po.setFinalPrice(model.getFinalPrice());
            po.setNextBid(model.getNextBid());
            po.setTransportationCost(model.getTransportationCost());
            po.setPoundageRate(model.getPoundageRate());
            po.setOpenTime(model.getOpenTime());
            po.setDeadline(model.getDeadline());
            po.setModifedTime(new Date());
            this.poMapper.updateByPrimaryKey(po);
        }
    }

    @Override
    public void delete(Long id) {
        LotPO po = new LotPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.poMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void patch(Long id, Lot model) {

        LotPO po = this.dozerMapper.map(model, LotPO.class);
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
