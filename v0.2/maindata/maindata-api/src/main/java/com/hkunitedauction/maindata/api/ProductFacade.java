package com.hkunitedauction.maindata.api;

import com.hkunitedauction.maindata.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product")
public interface ProductFacade {

    @GetMapping
    Product[] query();
}
