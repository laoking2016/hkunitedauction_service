package com.hkunitedauction.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.maindata.model.Image;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="OrderDetail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetail {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "orderId")
    private Long orderId;

    @ApiModelProperty(value = "contextId")
    private Long contextId;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "initPrice")
    private Double initPrice;

    @ApiModelProperty(value = "quantity")
    private Integer quantity;

    @ApiModelProperty(value = "discount")
    private Double discount;

    @ApiModelProperty(value = "finalPrice")
    private Double finalPrice;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "images")
    private Image[] images;

    @ApiModelProperty(value = "supplier name")
    private String supplierName;

    @ApiModelProperty(value = "supplier contact")
    private String supplierContact;

    @ApiModelProperty(value = "supplier wechat")
    private String supplierWechat;

    public void submit(Lot lot){
        this.supplierName = lot.getSupplierName();
        this.supplierContact = lot.getSupplierContact();
        this.supplierWechat = lot.getSupplierWechat();
        this.name = lot.getName();
        this.initPrice = lot.getInitPrice();
        this.quantity = 1;
        this.discount = 1D;
        this.finalPrice = lot.getFinalPrice();
        this.description = lot.getDescription();
    }
}
