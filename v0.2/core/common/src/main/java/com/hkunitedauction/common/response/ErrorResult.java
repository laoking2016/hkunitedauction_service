package com.hkunitedauction.common.response;

import lombok.Data;

@Data
public class ErrorResult {

    private Integer status;

    private String message;
}
