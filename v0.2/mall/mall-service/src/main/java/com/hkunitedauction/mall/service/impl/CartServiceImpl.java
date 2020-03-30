package com.hkunitedauction.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.mapper.CartPOMapper;
import com.hkunitedauction.mall.model.Cart;
import com.hkunitedauction.mall.model.CartPO;
import com.hkunitedauction.mall.service.CartService;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public int count(Example example) {
        return this.poMapper.selectCountByExample(example);
    }

    @Override
    public QueryResult<Cart> query(Example example, RowBounds rowBounds) {
        Cart[] list = this.poMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    Cart model = this.dozerMapper.map(e, Cart.class);
                    Map<Long, Integer> items = JSON.parseObject(e.getItems(), HashMap.class);
                    model.setItems(items);
                    return model;
                }).collect(Collectors.toList()).toArray(new Cart[0]);

        int totalCount = this.poMapper.selectCountByExample(example);
        QueryResult<Cart> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @Override
    public Long create(Cart model) {
        CartPO po = this.dozerMapper.map(model, CartPO.class);
        po.setId(null);
        if(model.getItems() != null){
            po.setItems(JSON.toJSONString(model.getItems()));
        }
        po.setDeleted(false);
        Date now = new Date();
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.poMapper.insert(po);
        return po.getId();
    }

    @Override
    public void update(Long id, Cart model) {
        CartPO po = this.poMapper.selectByPrimaryKey(id);

        if(po != null){
            po.setBuyer(model.getBuyer());
            if(model.getItems() != null){
                po.setItems(JSON.toJSONString(model.getItems()));
            }
            po.setModifedTime(new Date());
            this.poMapper.updateByPrimaryKey(po);
        }
    }

    @Override
    public void delete(Long id) {
        CartPO po = new CartPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.poMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void patch(Long id, Cart model) {
        CartPO po = this.dozerMapper.map(model, CartPO.class);
        po.setId(id);

        if(model.getItems() != null){
            po.setItems(JSON.toJSONString(model.getItems()));
        }

        this.poMapper.updateByPrimaryKeySelective(po);
    }
}
