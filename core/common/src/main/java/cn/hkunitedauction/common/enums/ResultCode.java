package cn.hkunitedauction.common.enums;


public enum ResultCode {

    SUCCESS(0, "OK"),
    SYSTEM_ERROR(1000, "System error");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){return this.code;}

    public String message(){return message;}
}
