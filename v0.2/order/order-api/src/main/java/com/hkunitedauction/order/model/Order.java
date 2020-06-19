package com.hkunitedauction.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.mall.model.Good;
import com.hkunitedauction.member.model.Member;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@ApiModel(value="Order")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "type")
    private OrderType type;

    @ApiModelProperty(value = "status")
    private OrderStatus status;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "contact")
    private String contact;

    @ApiModelProperty(value = "wechat")
    private String wechat;

    @ApiModelProperty(value = "address")
    private String address;

    @ApiModelProperty(value = "orderDate")
    private Date orderDate;

    @ApiModelProperty(value = "initAmount")
    private Double initAmount;

    @ApiModelProperty(value = "finalAmount")
    private Double finalAmount;

    @ApiModelProperty(value = "transportCost")
    private Double transportCost;

    @ApiModelProperty(value = "detail")
    private OrderDetail[] detail;

    private void setMember(Member member){
        if(member != null){
            this.name = member.getTrueName();
            this.contact = member.getContact();
            this.wechat = member.getWechat();
            this.address = member.getAddress();
        }
    }

    public void createOrder(Member member, Good[] goods){

        this.setMember(member);

        Map<Long, Good> map =
                Arrays.asList(goods).stream().collect(Collectors.toMap(Good::getId, e -> e));

        double initAmount = 0;
        double finalAmount = 0;

        for(OrderDetail item : detail){
            if(map.containsKey(item.getContextId())){
                Good good = map.get(item.getContextId());
                item.setSupplierName(good.getSupplierName());
                item.setSupplierContact(good.getSupplierContact());
                item.setSupplierWechat(good.getSupplierWechat());
                item.setName(good.getName());
                item.setInitPrice(good.getInitPrice());
                item.setQuantity(good.getQuantity());
                item.setDiscount(good.getDiscount());
                item.setFinalPrice(good.getFinalPrice());
                item.setDescription(good.getDescription());
                item.setImages(good.getImages());
            }

            initAmount += item.getInitPrice() * item.getQuantity();
            finalAmount += item.getFinalPrice() * item.getQuantity();
        }

        this.initAmount = initAmount;
        this.finalAmount = finalAmount;
    }

    public void createOrder(Member member, Lot lot){

        this.setMember(member);

        if(this.detail.length > 0){
            OrderDetail item = this.detail[0];
            item.setSupplierName(lot.getSupplierName());
            item.setSupplierContact(lot.getSupplierContact());
            item.setSupplierWechat(lot.getSupplierWechat());
            item.setName(lot.getName());
            item.setInitPrice(lot.getInitPrice());
            item.setFinalPrice(lot.getFinalPrice());
            item.setDescription(lot.getDescription());
            item.setImages(lot.getImages());
            this.initAmount = lot.getInitPrice();
            this.finalAmount = lot.getFinalPrice();
        }
    }
}
