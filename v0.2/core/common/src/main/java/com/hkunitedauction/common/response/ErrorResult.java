package com.hkunitedauction.common.response;

import com.hkunitedauction.common.enums.ResultCode;
import lombok.Data;

@Data
public class ErrorResult {

    private Integer status;

    private String message;

    private String exception;

    public static ErrorResult fail(ResultCode resultCode, Throwable e, String message){
        ErrorResult result = ErrorResult.fail(resultCode, e);
        result.setMessage(message);
        return result;
    }

    public static ErrorResult fail(ResultCode resultCode, Throwable e){
        ErrorResult result = new ErrorResult();
        result.setMessage(e.getMessage());
        //result.setMessage(resultCode.message());
        result.setStatus(resultCode.code());
        result.setException(e.getClass().getName());
        return result;
    }
}
