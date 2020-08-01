package com.hkunitedauction.console.client;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="lot", url = "${feign.lot.url:}")
public interface LotClient {
}
