package com.hkunitedauction.member.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Register")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Register {

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "true name")
    private String trueName;

    @ApiModelProperty(value = "contact")
    private String contact;

    @ApiModelProperty(value = "address")
    private String address;

    @ApiModelProperty(value = "email")
    private String email;
}
