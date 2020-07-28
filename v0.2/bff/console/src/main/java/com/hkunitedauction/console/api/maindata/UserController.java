package com.hkunitedauction.console.api.maindata;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.UserClient;
import com.hkunitedauction.maindata.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "User")
@RestController
@RequestMapping("/main-data/user")
public class UserController {

    @Autowired
    private UserClient client;

    @ApiOperation("query")
    @GetMapping
    public QueryResult<User> query(@RequestParam(value = "filter", required = false) String filter,
                            @RequestParam(value = "sort", required = false) String sort,
                            @RequestParam(value = "pagasize", required = false) Integer pagesize,
                            @RequestParam(value = "page", required = false) Integer page){
        return client.query(filter, sort, pagesize, page);
    }

    @ApiOperation("create")
    @PostMapping
    public Long create(@RequestBody User model){
        return client.create(model);
    }

    @ApiOperation("update")
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody User model){
        client.update(id, model);
    }

    @ApiOperation("delete")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        client.delete(id);
    }
}
