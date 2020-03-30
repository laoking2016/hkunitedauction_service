package com.hkunitedauction.member.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.member.model.*;
import com.hkunitedauction.member.service.MemberService;
import com.hkunitedauction.util.QueryBuilder;
import com.hkunitedauction.util.security.JwtProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Api(value = "Member")
@RestController
@RequiredArgsConstructor
public class MemberFacadeImpl implements MemberFacade {

    @Autowired
    private MemberService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private Mapper dozerMapper;

    @ApiOperation(value = "count")
    @Override
    public int count(@RequestParam(value = "filter", required = false) String filter){

        Example example = new Example(MemberPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));

        return this.service.count(example);
    }



    @ApiOperation(value = "query")
    @Override
    public QueryResult<Member> query(
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "pagasize", required = false) Integer pagesize,
            @RequestParam(value = "page", required = false) Integer page) {

        Example example = new Example(MemberPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        return this.service.query(example, rowBounds);
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestBody Member model) {

        return this.service.create(model);
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@PathVariable Long id, @RequestBody Member model) {
        this.service.update(id, model);
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@PathVariable Long id) {
       this.service.delete(id);
    }

    @ApiOperation(value = "patch")
    @Override
    public void patch(@PathVariable Long id, @RequestBody Member model) {
        if(model.getPassword() != null){
            model.setPassword(passwordEncoder.encode(model.getPassword()));
        }
        this.service.patch(id, model);
    }

    @Override
    public void register(@RequestBody Register model) {

        Example example = new Example(MemberPO.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleted", false);
        criteria.andEqualTo("name", model.getName());
        int count = this.service.count(example);
        Assert.isTrue(count == 0, "用户名已存在");

        if(model.getContact() != null){
            example = new Example(MemberPO.class);
            criteria = example.createCriteria();
            criteria.andEqualTo("deleted", false);
            criteria.andEqualTo("contact", model.getContact());
            count = this.service.count(example);
            Assert.isTrue(count == 0, "手机号已存在");
        }

        if(model.getEmail() != null){
            example = new Example(MemberPO.class);
            criteria = example.createCriteria();
            criteria.andEqualTo("deleted", false);
            criteria.andEqualTo("email", model.getEmail());
            count = this.service.count(example);
            Assert.isTrue(count == 0, "邮箱已存在");
        }

        model.setPassword(this.passwordEncoder.encode(model.getPassword()));
        Member member = this.dozerMapper.map(model, Member.class);
        this.service.create(member);
    }

    @ApiOperation(value = "login")
    @Override
    public Credential login(@RequestBody Login model){

        Example example = new Example(MemberPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", model.getName());
        criteria.andEqualTo("deleted", false);
        QueryResult<Member> result = this.service.query(example, new RowBounds(0, 1));

        Assert.isTrue(result.getTotalCount() > 0, "用户名/手机/邮箱/密码错误");
        Member member = result.getList()[0];
        Assert.isTrue(this.passwordEncoder.matches(model.getPassword(), member.getPassword()), "用户名/手机/邮箱/密码错误");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("member"));
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(model.getName(), null, authorities);

        return Credential.build(model.getName(), jwtProvider.generateJwtToken(authentication));
    }

    @Override
    public Credential wechatLogin(String code, String state) {
        return null;
    }

}
