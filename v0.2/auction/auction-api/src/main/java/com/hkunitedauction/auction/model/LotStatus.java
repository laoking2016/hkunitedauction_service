package com.hkunitedauction.auction.model;

import java.util.HashMap;
import java.util.Map;

public enum LotStatus {
    DRAFT(10),
    PUBLISHED(20),
    ONSALE(30),
    CLOSED(40);

    private int value;
    private static Map map = new HashMap<>();

    private LotStatus(int value) {
        this.value = value;
    }

    static {
        for (LotStatus role : LotStatus.values()) {
            map.put(role.value, role);
        }
    }

    public static LotStatus valueOf(int status) {
        return (LotStatus) map.get(status);
    }

    public int getValue() {
        return value;
    }
}
