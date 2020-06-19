package com.hkunitedauction.order.service.Impl;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.order.mapper.OrderPOMapper;
import com.hkunitedauction.order.model.*;
import com.hkunitedauction.order.service.OrderDetailService;
import com.hkunitedauction.order.service.OrderService;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;

    @Autowired
    private OrderDetailService detailService;

    @Override
    public int count(Example example) {
        return this.poMapper.selectCountByExample(example);
    }

    @Override
    public QueryResult<Order> query(Example example, RowBounds rowBounds) {

        Order[] list = this.poMapper.selectByExampleAndRowBounds(example, rowBounds)
            .stream().map(e -> {
                Order model = this.dozerMapper.map(e, Order.class);
                model.setStatus(OrderStatus.valueOf(e.getStatus()));
                model.setType(OrderType.valueOf(e.getType()));
                if(e.getDetail() != null){
                    model.setDetail(JSON.parseObject(e.getDetail(), OrderDetail[].class));
                }
                return model;
            }).collect(Collectors.toList()).toArray(new Order[0]);

        int totalCount = this.poMapper.selectCountByExample(example);
        QueryResult<Order> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @Override
    public Long create(Order model) {
        Date now = new Date();

        OrderPO po = this.dozerMapper.map(model, OrderPO.class);
        po.setId(null);
        if(model.getDetail() != null){
            po.setDetail(JSON.toJSONString(model.getDetail()));
        }
        po.setType(model.getType().getValue());
        po.setStatus(OrderStatus.SUBMITTED.getValue());
        po.setDeleted(false);

        po.setOrderDate(now);
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.poMapper.insert(po);

        if(model.getDetail() != null){
            for(OrderDetail item : model.getDetail()){
                item.setOrderId(po.getId());
                this.detailService.create(item);
            }
        }

        return po.getId();
    }

    @Override
    public void delete(Long id) {
        OrderPO po = new OrderPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.poMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void patch(Long id, Order model) {
        OrderPO po = this.dozerMapper.map(model, OrderPO.class);
        po.setId(id);

        if(model.getStatus() != null){
            po.setStatus(model.getStatus().getValue());
        }

        if(model.getType() != null){
            po.setType(model.getType().getValue());
        }

        if(model.getDetail() != null){
            po.setDetail(JSON.toJSONString(model.getDetail()));
        }

        this.poMapper.updateByPrimaryKeySelective(po);
    }
}
