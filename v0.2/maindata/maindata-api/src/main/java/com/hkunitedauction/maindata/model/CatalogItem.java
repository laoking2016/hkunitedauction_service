package com.hkunitedauction.maindata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="CatalogItem")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogItem {

    @ApiModelProperty(value = "Name")
    private String name;

    @ApiModelProperty(value = "Child Catalogs")
    private CatalogItem[] children;
}
