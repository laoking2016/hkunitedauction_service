package com.hkunitedauction.mall.event;

public interface GoodUpdateEventReceiver {
    void process(GoodUpdateEvent event);
}
