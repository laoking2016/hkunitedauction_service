package com.hkunitedauction.auction.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="Bid")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bid {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "parent id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long parentId;

    @ApiModelProperty(value = "bidder name")
    private String bidderName;

    @ApiModelProperty(value = "bid price")
    private Double bidPrice;

    @ApiModelProperty(value = "bid time")
    private Date bidTime;
}
