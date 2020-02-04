package com.hkunitedauction.sample.service;

import com.hkunitedauction.sample.api.HelloFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Hello world")
@RestController
public class HelloFacadeImpl implements HelloFacade {

    @Value("${book.config}")
    private String msg;

    @ApiOperation(value = "Hello word")
    @Override
    @GetMapping
    public String get(){

        return "Hello maindata." + msg;
    }
}
