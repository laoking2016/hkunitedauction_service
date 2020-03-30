package com.hkunitedauction.member.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Login")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "password")
    private String password;
}
