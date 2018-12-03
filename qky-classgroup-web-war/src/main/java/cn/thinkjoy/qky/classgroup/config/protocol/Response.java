package cn.thinkjoy.qky.classgroup.config.protocol;


import cn.thinkjoy.common.exception.BizException;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable {
    /** 返回的响应码 为空，说明是正常返回*/
    private String code;
    /** 异常信息 */
    private String message;
    /** 时间 */
    private Date timestamp;

    public Response(String status,String message,Date timestamp) {
        this.code = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Response(BizException bizException){
        this.code = bizException.getErrorCode();
        this.message = bizException.getMsg();
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
