package com.hkunitedauction.maindata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Image")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "vpath")
    private String vpath;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "icon name")
    private String iconName;

    @ApiModelProperty(value = "icon vpath")
    private String iconVpath;

    @ApiModelProperty(value = "icon url")
    private String iconUrl;
}
