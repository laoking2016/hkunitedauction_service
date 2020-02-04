package com.hkunitedauction.maindata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @ApiModelProperty(value = "Id")
    private Integer id;

    @ApiModelProperty(value = "Name")
    private String name;

    @ApiModelProperty(value = "Description")
    private String description;
}
