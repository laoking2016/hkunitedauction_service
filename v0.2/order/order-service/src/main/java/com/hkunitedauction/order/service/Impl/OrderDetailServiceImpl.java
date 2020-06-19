package com.hkunitedauction.order.service.Impl;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.Image;
import com.hkunitedauction.order.mapper.OrderDetailPOMapper;
import com.hkunitedauction.order.model.OrderDetail;
import com.hkunitedauction.order.model.OrderDetailPO;
import com.hkunitedauction.order.service.OrderDetailService;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public int count(Example example) {
        return this.poMapper.selectCountByExample(example);
    }

    @Override
    public QueryResult<OrderDetail> query(Example example, RowBounds rowBounds) {
        OrderDetail[] list = this.poMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    OrderDetail model = this.dozerMapper.map(e, OrderDetail.class);
                    if(e.getImages() != null){
                        model.setImages(JSON.parseObject(e.getImages(), Image[].class));
                    }
                    return model;
                }).collect(Collectors.toList()).toArray(new OrderDetail[0]);

        int totalCount = this.poMapper.selectCountByExample(example);
        QueryResult<OrderDetail> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @Override
    public Long create(OrderDetail model) {
        OrderDetailPO po = this.dozerMapper.map(model, OrderDetailPO.class);
        po.setId(null);
        if(model.getImages() != null){
            po.setImages(JSON.toJSONString(model.getImages()));
        }
        po.setDeleted(false);
        Date now = new Date();
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.poMapper.insert(po);
        return po.getId();
    }

    @Override
    public void delete(Long id) {
        OrderDetailPO po = new OrderDetailPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.poMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void patch(Long id, OrderDetail model) {
        OrderDetailPO po = this.dozerMapper.map(model, OrderDetailPO.class);
        po.setId(id);

        if(model.getImages() != null){
            po.setImages(JSON.toJSONString(model.getImages()));
        }

        this.poMapper.updateByPrimaryKeySelective(po);
    }
}
