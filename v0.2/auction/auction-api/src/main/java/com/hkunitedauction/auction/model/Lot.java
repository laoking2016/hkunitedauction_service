package com.hkunitedauction.auction.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.maindata.model.Image;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.util.Assert;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ApiModel(value="Lot")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lot {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "status")
    private LotStatus status;

    @ApiModelProperty(value = "level 1")
    private String l1;

    @ApiModelProperty(value = "level 2")
    private String l2;

    @ApiModelProperty(value = "level 3")
    private String l3;

    @ApiModelProperty(value = "supplier name")
    private String supplierName;

    @ApiModelProperty(value = "supplier contact")
    private String supplierContact;

    @ApiModelProperty(value = "supplier wechat")
    private String supplierWechat;

    @ApiModelProperty(value = "winner name")
    private String winnerName;

    @ApiModelProperty(value = "winner true name")
    private String winnerTrueName;

    @ApiModelProperty(value = "winner contact")
    private String winnerContact;

    @ApiModelProperty(value = "winner wechat")
    private String winnerWechat;

    @ApiModelProperty(value = "winner address")
    private String winnerAddress;

    @ApiModelProperty(value = "bid time")
    private Date bidTime;

    @ApiModelProperty(value = "final initPrice")
    private Double finalPrice;

    @ApiModelProperty(value = "init initPrice")
    private Double initPrice;

    @ApiModelProperty(value = "next bid")
    private Double nextBid;

    @ApiModelProperty(value = "transportation cost")
    private Double transportationCost;

    @ApiModelProperty(value = "poundage rate")
    private Double poundageRate;

    @ApiModelProperty(value = "open time")
    private Date openTime;

    @NotNull(message = "结束时间不能为空")
    @ApiModelProperty(value = "deadline")
    private Date deadline;

    @ApiModelProperty(value = "images")
    private Image[] images;

    @ApiModelProperty(value = "payments")
    private String[] payments;

    public void validate(){

        Calendar c = Calendar.getInstance();
        Date now = new Date();

        c.setTime(now);
        c.add(Calendar.DATE, 7);

        Assert.isTrue(this.deadline.compareTo(c.getTime()) > 0, "结束时间必须在7天以后");

        Date dt = null;
        try{

            SimpleDateFormat df = new SimpleDateFormat();
            if(this.openTime == null){
                dt = df.parse("1949/10/1");
            }else{
                dt = this.openTime;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        Assert.isTrue(this.deadline.compareTo(dt) > 0, "结束时间必须大于起始时间");
    }

    public void publish(){
        if(this.openTime == null) {
            DateFormat df = new SimpleDateFormat();
            try{
                this.openTime = df.parse("1949/10/1");
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        if(this.getInitPrice() == 0) {
            this.nextBid = 0d;
        }else {
            this.nextBid = this.initPrice;
        }
        this.finalPrice = this.nextBid;
        this.status = LotStatus.PUBLISHED;
    }

    public void validate(Bid bid){
        Date now = new Date();
        Assert.isTrue(this.deadline.compareTo(now) > 0, "竞买已结束");
        if(this.winnerName == null){
            Assert.isTrue(bid.getBidPrice() >= this.nextBid, "出价不能低于起始价");
        }else{
            if(this.winnerName.equalsIgnoreCase(bid.getBidderName())){
                Assert.isTrue(bid.getBidPrice() > this.finalPrice, "出价不能低于您的最高价" );
            }else{
                Assert.isTrue(bid.getBidPrice() > this.nextBid, "出价需要大于代理价");
            }
        }
    }

    public void bid(List<Bid> bids) {
        bids =
                bids.stream().sorted((e1, e2) -> Double.compare(e1.getBidPrice(), e2.getBidPrice())).collect(Collectors.toList());
        int size = bids.size();

        if (size == 0) {
            this.nextBid = this.initPrice;
            return;
        }

        this.finalPrice = bids.get(size - 1).getBidPrice();
        this.winnerName = bids.get(size - 1).getBidderName();

        if (size == 1) {
            this.nextBid = bids.get(size - 1).getBidPrice();
            return;
        }

        double first = bids.get(size - 1).getBidPrice();
        double second = bids.get(size - 2).getBidPrice();

        double inc = 0;

        if (second < 100) {
            inc = 5d;
        } else if (second >= 100 && second < 1000) {
            inc = 10d;
        } else {
            inc = 20d;
        }

        double agent = second + inc;

        if (agent > first) {
            this.nextBid = first;
        } else {
            this.nextBid = agent;
        }
    }
}
