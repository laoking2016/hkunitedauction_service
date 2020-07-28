package com.hkunitedauction.console.client;

import com.hkunitedauction.maindata.model.Catalog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="catalog", url = "${feign.catalog.url:}")
public interface CatalogClient {

    @GetMapping
    Catalog query();

    @PutMapping()
    void update(@RequestBody Catalog model);
}
