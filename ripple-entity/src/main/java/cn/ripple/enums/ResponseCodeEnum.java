package cn.ripple.enums;

import lombok.Data;

/**
 * 服务器响应code
 */
public enum ResponseCodeEnum {
    SUCCESS(200, "成功"),
    SYSTEM_ERROR(500, "系统错误"),
    ACCESS_DENY(403, "没有权限访问"),
    BUSINESS_ERROR(1001, "业务错误"),
    TOKEN_TIME_OUT(1003, "token超时");

    ResponseCodeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    private final Integer value;
    private final String name;

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
