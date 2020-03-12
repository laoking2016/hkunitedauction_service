package com.hkunitedauction.maindata.service;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.api.ProductFacade;
import com.hkunitedauction.maindata.mapper.ProductPOMapper;
import com.hkunitedauction.maindata.model.Image;
import com.hkunitedauction.maindata.model.Product;
import com.hkunitedauction.maindata.model.ProductPO;
import com.hkunitedauction.util.QueryBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "Product")
@RestController
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductPOMapper productPOMapper;

    @Autowired
    private Mapper dozerMapper;

    @ApiOperation(value = "query")
    @Override
    public QueryResult<Product> query(HttpServletRequest reqeust) {

        Map<String, String[]> params = reqeust.getParameterMap();

        Example example = QueryBuilder.buildExample(params, ProductPO.class);
        RowBounds rowBounds = QueryBuilder.buildRowBounds(params);

        int totalCount = this.productPOMapper.selectCountByExample(example);
        Product[] list = this.productPOMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    Product product = dozerMapper.map(e, Product.class);
                    if(e.getImages() != null && e.getImages() != ""){
                        product.setImages(JSON.parseObject(e.getImages(), Image[].class));
                    }
                    return product;
                }).collect(Collectors.toList()).toArray(new Product[0]);

        QueryResult<Product> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @ApiOperation(value = "get")
    @Override
    public Product get(@PathVariable Long id) {

        ProductPO po = this.productPOMapper.selectByPrimaryKey(id);

        if(po == null){
            return null;
        }

        Product model = this.dozerMapper.map(po, Product.class);
        model.setImages(JSON.parseObject(po.getImages(), Image[].class));

        return model;
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestBody Product model) {

        ProductPO po = this.dozerMapper.map(model, ProductPO.class);
        po.setId(null);
        if(model.getImages() != null){
            po.setImages(JSON.toJSONString(model.getImages()));
        }
        po.setDeleted(false);
        Date now = new Date();
        po.setCreatedTime(now);
        po.setModifedTime(now);
        this.productPOMapper.insert(po);
        return po.getId();
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@PathVariable Long id, @RequestBody Product model) {

        ProductPO po = this.productPOMapper.selectByPrimaryKey(id);

        if(po != null){
            po.setName(model.getName());
            po.setDescription(model.getDescription());
            po.setL1(model.getL1());
            po.setL2(model.getL2());
            po.setL3(model.getL3());
            if(model.getImages() != null){
                po.setImages(JSON.toJSONString(model.getImages()));
            }
            po.setModifedTime(new Date());
            this.productPOMapper.updateByPrimaryKey(po);
        }
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@PathVariable Long id) {

        ProductPO po = new ProductPO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.productPOMapper.updateByPrimaryKeySelective(po);
    }
}
