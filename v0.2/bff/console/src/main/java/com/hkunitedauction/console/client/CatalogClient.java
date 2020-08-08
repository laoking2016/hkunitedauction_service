package com.hkunitedauction.console.client;

import com.hkunitedauction.maindata.api.CatalogFacade;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(name="catalog", url = "${feign.catalog.url:}")
public interface CatalogClient extends CatalogFacade {

}
