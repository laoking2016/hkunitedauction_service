package com.hkunitedauction.mall.component.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisPool {

    @Autowired
    private JedisPool pool;

    public void execute(CallWithJedis caller){
        try(Jedis jedis = pool.getResource()){
            caller.call(jedis);
        }
    }
}
