package com.hkunitedauction.maindata.model;

import java.util.HashMap;
import java.util.Map;

public enum  Role {
    ADMIN(1),
    SUPPLIER(2);

    private int value;
    private static Map map = new HashMap<>();

    private Role(int value) {
        this.value = value;
    }

    static {
        for (Role role : Role.values()) {
            map.put(role.value, role);
        }
    }

    public static Role valueOf(int role) {
        return (Role) map.get(role);
    }

    public int getValue() {
        return value;
    }
}
