package com.hkunitedauction.maindata.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.mapper.UserPOMapper;
import com.hkunitedauction.maindata.model.Role;
import com.hkunitedauction.maindata.model.User;
import com.hkunitedauction.maindata.model.UserPO;
import com.hkunitedauction.util.QueryBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "User")
@RestController
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserPOMapper userPOMapper;

    @Autowired
    private Mapper dozerMapper;

    @ApiOperation(value = "query")
    @Override
    public QueryResult<User> query(@RequestParam(value = "filter", required = false) String filter,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                   @RequestParam(value = "page", required = false) Integer page) {

        Example example = new Example(UserPO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter), "name");
        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        int totalCount = this.userPOMapper.selectCountByExample(example);
        User[] list = this.userPOMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    User model = dozerMapper.map(e, User.class);
                    model.setRole(Role.valueOf(e.getRole()));
                    return model;
                }).collect(Collectors.toList()).toArray(new User[0]);

        QueryResult<User> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestBody User model) {

        UserPO po = this.dozerMapper.map(model, UserPO.class);
        po.setId(null);
        po.setRole(model.getRole().getValue());
        po.setDeleted(false);
        Date now = new Date();
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.userPOMapper.insert(po);
        return po.getId();
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@PathVariable Long id, @RequestBody User model) {

        UserPO po = this.userPOMapper.selectByPrimaryKey(id);

        if(po != null){
            po.setName(model.getName());
            po.setPassword(model.getPassword());
            po.setTrueName(model.getTrueName());
            po.setContact(model.getContact());
            po.setRole(model.getRole().getValue());
            po.setModifedTime(new Date());
            this.userPOMapper.updateByPrimaryKey(po);
        }
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@PathVariable Long id) {

        UserPO po = new UserPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.userPOMapper.updateByPrimaryKeySelective(po);
    }
}
