package com.hkunitedauction.order.model;

import java.util.HashMap;
import java.util.Map;

public enum  OrderStatus {
    SUBMITTED(10),
    PAYED(20),
    SHIPPING(30),
    RECEIVED(40),
    CLOSED(50);

    private int value;
    private static Map map = new HashMap<>();

    private OrderStatus(int value) {
        this.value = value;
    }

    static {
        for (OrderStatus status : OrderStatus.values()) {
            map.put(status.value, status);
        }
    }

    public static OrderStatus valueOf(int status) {
        return (OrderStatus) map.get(status);
    }

    public int getValue() {
        return value;
    }
}
