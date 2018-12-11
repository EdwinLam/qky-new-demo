package cn.ripple.config.protocol;

import cn.ripple.exception.RippleException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Response implements Serializable {
    /** 返回的响应码 为空，说明是正常返回*/
    private Integer code;
    /** 异常信息 */
    private String message;
    /** 时间 */
    private Date timestamp;

    public Response(Integer code,String message,Date timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Response(RippleException rippleException){
        this.code = rippleException.getCode();
        this.message = rippleException.getMessage();
        this.timestamp = new Date();
    }

    public HashMap toMap(){
        HashMap valueMap = new HashMap();
        valueMap.put("code",this.code);
        valueMap.put("message",this.message);
        valueMap.put("timestamp",this.timestamp);
        return valueMap;
    }
}
