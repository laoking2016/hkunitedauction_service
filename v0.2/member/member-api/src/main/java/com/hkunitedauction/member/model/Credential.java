package com.hkunitedauction.member.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Credential")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Credential {

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "name")
    private String token;

    public static Credential build(String name, String token){
        Credential c = new Credential();
        c.setName(name);
        c.setToken(token);
        return c;
    }
}
