package com.hkunitedauction.order.model;

import java.util.HashMap;
import java.util.Map;

public enum  OrderType {
    AUCTION(10),
    MALL(20);

    private int value;
    private static Map map = new HashMap<>();

    private OrderType(int value) {
        this.value = value;
    }

    static {
        for (OrderType type : OrderType.values()) {
            map.put(type.value, type);
        }
    }

    public static OrderType valueOf(int type) {
        return (OrderType) map.get(type);
    }

    public int getValue() {
        return value;
    }
}