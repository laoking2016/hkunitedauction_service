package com.hkunitedauction.maindata.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "ServiceOrder")
@RestController
@RequestMapping("/server/order")
public class ServiceOrderController {

    @GetMapping("/details")
    public String getDetails(@RequestParam List<Long> ids){
        for(Long id: ids){
            Long i = id;
        }
        return "success";
    }
}
