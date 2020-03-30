package com.hkunitedauction.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Member")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "name")
    private String name;

    @JsonIgnore
    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "true name")
    private String trueName;

    @ApiModelProperty(value = "contact")
    private String contact;

    @JsonIgnore
    @ApiModelProperty(value = "wechat")
    private String wechat;

    @ApiModelProperty(value = "address")
    private String address;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "avatar")
    private String avatar;
}
