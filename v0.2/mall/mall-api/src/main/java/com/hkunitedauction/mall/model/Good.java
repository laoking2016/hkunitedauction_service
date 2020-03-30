package com.hkunitedauction.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.maindata.model.Image;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Good")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Good {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "status")
    private GoodStatus status;

    @ApiModelProperty(value = "level 1")
    private String l1;

    @ApiModelProperty(value = "level 2")
    private String l2;

    @ApiModelProperty(value = "level 3")
    private String l3;

    @ApiModelProperty(value = "init price")
    private Double initPrice;

    @ApiModelProperty(value = "quantity")
    private Integer quantity;

    @ApiModelProperty(value = "discount")
    private Double discount;

    @ApiModelProperty(value = "final price")
    private Double finalPrice;

    @ApiModelProperty(value = "transportation cost")
    private Double transportationCost;

    @ApiModelProperty(value = "poundage rate")
    private Double poundageRate;

    @ApiModelProperty(value = "images")
    private Image[] images;

    @ApiModelProperty(value = "payments")
    private String[] payments;
}
