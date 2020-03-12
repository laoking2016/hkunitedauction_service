package com.hkunitedauction.util;

import tk.mybatis.mapper.genid.GenId;

public class UUIdGenId implements GenId<Long> {

    private SnowFlake snowFlake = new SnowFlake(2, 3);

    @Override
    public Long genId(String table, String column) {
        return snowFlake.nextId();
    }
}
