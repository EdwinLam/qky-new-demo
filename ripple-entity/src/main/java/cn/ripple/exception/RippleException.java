package cn.ripple.exception;

import cn.ripple.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class RippleException extends RuntimeException {

    private String message;
    private Integer code = ResponseCodeEnum.BUSINESS_ERROR.getValue();

    public RippleException(String message){
        super(message);
        this.message = message;
    }

    public RippleException(Integer code, String msg){
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public RippleException(ResponseCodeEnum responseCodeEnum){
        super(responseCodeEnum.getName());
        this.message = responseCodeEnum.getName();
        this.code = responseCodeEnum.getValue();
    }

    public RippleException(ResponseCodeEnum responseCodeEnum, String message){
        super(responseCodeEnum.getName());
        this.message = message;
        this.code = responseCodeEnum.getValue();
    }
}
