package com.hkunitedauction.maindata.service;

import com.hkunitedauction.maindata.api.ProductFacade;
import com.hkunitedauction.maindata.mapper.ProductPOMapper;
import com.hkunitedauction.maindata.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Api(value = "Product")
@RestController
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductPOMapper productPOMapper;

    @Autowired
    private Mapper dozerMapper;

    @ApiOperation(value = "Product")
    @Override
    @GetMapping
    public Product[] query() {

        return
            productPOMapper.selectAll().stream().map(e -> dozerMapper.map(e, Product.class)).collect(Collectors.toList()).toArray(new Product[0]);

    }
}
