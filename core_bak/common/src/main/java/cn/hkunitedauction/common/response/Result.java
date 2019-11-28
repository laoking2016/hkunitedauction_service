package cn.hkunitedauction.common.response;

import cn.hkunitedauction.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {

    private Integer status;

    private String desc;

    private T Data;

    public static Result suc(){
        Result result = new Result();
        result.setStatusCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result suc(Object data){
        Result result = new Result();
        result.setStatusCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result fail(Integer status, String desc){
        Result result = new Result();
        result.setStatus(status);
        result.setDesc(desc);

        return result;
    }

    public static Result fail(ResultCode resultCode){
        Result result = new Result();
        result.setStatus(resultCode.code());
        result.setDesc(resultCode.message());

        return result;
    }

    private void setStatusCode(ResultCode code){
        this.status = code.code();
        this.desc = code.message();
    }
}
