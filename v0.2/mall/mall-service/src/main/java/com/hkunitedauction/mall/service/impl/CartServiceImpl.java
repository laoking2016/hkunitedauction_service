package com.hkunitedauction.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.mall.component.redis.Holder;
import com.hkunitedauction.mall.component.redis.RedisPool;
import com.hkunitedauction.mall.mapper.CartPOMapper;
import com.hkunitedauction.mall.model.Cart;
import com.hkunitedauction.mall.model.CartPO;
import com.hkunitedauction.mall.service.CartService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Pipeline;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartPOMapper poMapper;

    @Autowired
    private Mapper dozerMapper;

    @Autowired
    private RedisPool redis;

    @Override
    public QueryResult<Cart> query(String user) {

        String cartKey = String.format("cart:%s", user);
        Holder<Map<String, String>> mapHolder = new Holder<>();
        redis.execute(jedis -> {
            Map<String, String> map = jedis.hgetAll(cartKey);
            mapHolder.value(map);
        });

        List<Cart> list = new ArrayList<>();
        Map<Long, Integer> convertedMap = new HashMap<>();
        for(String key: mapHolder.value().keySet()){
            convertedMap.put(Long.parseLong(key), Integer.parseInt(mapHolder.value().get(key)));
        }

        list.add(Cart.builder().buyer(user).items(convertedMap).build());
        QueryResult<Cart> result = new QueryResult<>();
        result.setTotalCount(list.size());
        result.setList(list.toArray(new Cart[0]));
        return result;
    }

    @Override
    public void create(String user, Long goodId) {

        String cartKey = String.format("cart:%s", user);
        redis.execute(jedis -> {
            jedis.hset(cartKey, goodId.toString(), "1");
        });
    }

    @Override
    public void update(String user, Long goodId, Integer quantity) {

        String cartKey = String.format("cart:%s", user);
         redis.execute(jedis -> {
             jedis.hset(cartKey, goodId.toString(), quantity.toString());
         });
    }

    @Override
    public void delete(String user, Long[] goodIds) {

        String cartKey = String.format("cart:%s", user);
        redis.execute(jedis -> {
            Pipeline p = jedis.pipelined();
            for(Long goodId: goodIds){
                jedis.hdel(cartKey, goodId.toString());
            }
            p.sync();
        });
    }
}
