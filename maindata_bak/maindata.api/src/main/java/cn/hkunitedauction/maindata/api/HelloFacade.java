package cn.hkunitedauction.maindata.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/hello")
public interface HelloFacade {

    @GetMapping
    String get();
}
