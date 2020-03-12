package com.hkunitedauction.maindata.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
public interface UserFacade {

    @GetMapping
    QueryResult<User> query(HttpServletRequest reqeust);

    @PostMapping
    Long create(@RequestBody User model);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody User model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
