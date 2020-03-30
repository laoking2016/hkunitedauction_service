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
@ApiModel(value="CartItem")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItem {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "parent id")
    private Long parentId;

    @ApiModelProperty(value = "good id")
    private Long GoodId;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "l1")
    private String l1;

    @ApiModelProperty(value = "l2")
    private String l2;

    @ApiModelProperty(value = "l3")
    private String l3;

    @ApiModelProperty(value = "payments")
    private String[] payments;

    @ApiModelProperty(value = "init price")
    private Double initPrice;

    @ApiModelProperty(value = "quantity")
    private Integer quantity;

    @ApiModelProperty(value = "discount")
    private Double discount;

    @ApiModelProperty(value = "final price")
    private Double finalPrice;

    @ApiModelProperty(value = "images")
    private Image[] images;

    public static CartItem build(Good good){
        CartItem model = new CartItem();
        model.setName(good.getName());
        model.setDescription(good.getDescription());
        model.setL1(good.getL1());
        model.setL2(good.getL2());
        model.setL3(good.getL3());
        model.setPayments(good.getPayments());
        model.setInitPrice(good.getInitPrice());
        model.setQuantity(1);
        model.setDiscount(good.getDiscount());
        model.setFinalPrice(good.getFinalPrice());
        model.setImages(good.getImages());
        return model;
    }

    public void increment(){
        this.quantity++;
    }
}
