package com.hkunitedauction.mall.model;

import java.util.HashMap;
import java.util.Map;

public enum GoodStatus {
    DRAFT(10),
    PUBLISHED(20);

    private int value;
    private static Map map = new HashMap<>();

    private GoodStatus(int value) {
        this.value = value;
    }

    static {
        for (GoodStatus role : GoodStatus.values()) {
            map.put(role.value, role);
        }
    }

    public static GoodStatus valueOf(int status) {
        return (GoodStatus) map.get(status);
    }

    public int getValue() {
        return value;
    }
}
