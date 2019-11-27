package cn.hkunitedauction.maindata.service;

import cn.hkunitedauction.maindata.api.HelloFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Hello world")
@RestController
public class HelloFacadeImpl implements HelloFacade {

    @ApiOperation(value = "Hello word")
    @Override
    @GetMapping
    public String get(){
        return "Hello spring boot.";
    }
}
