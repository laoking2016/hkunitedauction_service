package com.hkunitedauction.console.client;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="user", url = "${feign.user.url:}")
public interface UserClient {

    @GetMapping
    QueryResult<User> query(@RequestParam(value = "filter", required = false) String filter,
                            @RequestParam(value = "sort", required = false) String sort,
                            @RequestParam(value = "pagasize", required = false) Integer pagesize,
                            @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestBody User model);

    @PutMapping("/{id}")
    void update(@PathVariable("id") Long id, @RequestBody User model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);


}
