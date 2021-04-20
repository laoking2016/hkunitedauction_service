package com.hkunitedauction.mall.component.redis;

import redis.clients.jedis.Jedis;

public interface CallWithJedis {
    public void call(Jedis jedis);
}
