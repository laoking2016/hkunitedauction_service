package com.hkunitedauction.console.api.member;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.MemberClient;
import com.hkunitedauction.member.api.MemberFacade;
import com.hkunitedauction.member.model.Credential;
import com.hkunitedauction.member.model.Login;
import com.hkunitedauction.member.model.Member;
import com.hkunitedauction.member.model.Register;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "member")
@RestController
@RequestMapping("/member/member")
public class MemberController implements MemberFacade {

    @Autowired
    private MemberClient client;

    @GetMapping("/count")
    @ApiOperation("count")
    public int count(@RequestParam(value = "filter", required = false) String filter){
        return client.count(filter);
    }

    @GetMapping
    @ApiOperation("query")
    public QueryResult<Member> query(
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "pagasize", required = false) Integer pagesize,
            @RequestParam(value = "page", required = false) Integer page){
        return client.query(filter, sort, pagesize, page);
    }

    @PostMapping
    @ApiOperation("create")
    public Long create(@RequestBody Member model){
        return client.create(model);
    }

    @PutMapping("/{id}")
    @ApiOperation("update")
    public void update(@PathVariable("id") Long id, @RequestBody Member model){
        client.update(id, model);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete")
    public void delete(@PathVariable("id") Long id){
        client.delete(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("patch")
    public void patch(@PathVariable("id") Long id, @RequestBody Member model){
        client.patch(id, model);
    }

    @PostMapping("/register")
    @ApiOperation("register")
    public void register(@RequestBody Register model){
        client.register(model);
    }

    @PostMapping("/login")
    @ApiOperation("login")
    public Credential login(@RequestBody Login model){
        return client.login(model);
    }

    @PostMapping("/wechatlogin")
    @ApiOperation("wechat login")
    public Credential wechatLogin(@RequestParam("code") String code, @RequestParam("state") String state){
        return client.wechatLogin(code, state);
    }

}
