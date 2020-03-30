package com.hkunitedauction.member.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.member.model.Credential;
import com.hkunitedauction.member.model.Login;
import com.hkunitedauction.member.model.Member;
import com.hkunitedauction.member.model.Register;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
public interface MemberFacade {

    @GetMapping("/count")
    int count(@RequestParam(value = "filter", required = false) String filter);



    @GetMapping
    QueryResult<Member> query(
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "pagasize", required = false) Integer pagesize,
            @RequestParam(value = "page", required = false) Integer page);

    @PostMapping
    Long create(@RequestBody Member model);

    @PutMapping("/{id}")
    void update(@PathVariable("id") Long id, @RequestBody Member model);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    void patch(@PathVariable("id") Long id, @RequestBody Member model);

    @PostMapping("/register")
    void register(@RequestBody Register model);

    @PostMapping("/login")
    Credential login(@RequestBody Login model);

    @PostMapping("/wechatlogin")
    Credential wechatLogin(@RequestParam("code") String code, @RequestParam("state") String state);

}
