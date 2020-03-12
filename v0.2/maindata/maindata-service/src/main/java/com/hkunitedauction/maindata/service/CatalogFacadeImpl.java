package com.hkunitedauction.maindata.service;

import com.alibaba.fastjson.JSON;
import com.hkunitedauction.maindata.api.CatalogFacade;
import com.hkunitedauction.maindata.mapper.CatalogPOMapper;
import com.hkunitedauction.maindata.model.Catalog;
import com.hkunitedauction.maindata.model.CatalogItem;
import com.hkunitedauction.maindata.model.CatalogPO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(value = "Catalog")
@RestController
@RequiredArgsConstructor
public class CatalogFacadeImpl implements CatalogFacade {

    @Autowired
    private CatalogPOMapper catalogPOMapper;

    @Autowired
    private Mapper dozerMapper;

    @ApiOperation(value = "query")
    @Override
    public Catalog query() {

        CatalogPO[] pos = this.catalogPOMapper.selectAll().toArray(new CatalogPO[0]);

        if(pos.length == 0){
            return null;
        }

        CatalogPO po = null;
        po = pos[0];

        Catalog model = new Catalog();
        model.setId(po.getId());
        model.setChildren(JSON.parseObject(po.getChildren(), CatalogItem[].class));

        return model;
    }

    @ApiOperation(value = "update")
    @Override
    public void update(@RequestBody Catalog model) {

        CatalogPO[] pos = this.catalogPOMapper.selectAll().toArray(new CatalogPO[0]);
        Date now = new Date();

        CatalogPO po = null;
        if(pos.length == 0){
            po = new CatalogPO();
            po.setId(null);
            po.setChildren(JSON.toJSONString(model.getChildren()));
            po.setCreatedTime(now);
            po.setModifedTime(now);
            this.catalogPOMapper.insert(po);
        }else {
            po = pos[0];
            po.setChildren(JSON.toJSONString(model.getChildren()));
            po.setModifedTime(now);
            this.catalogPOMapper.updateByPrimaryKey(po);
        }
    }
}
