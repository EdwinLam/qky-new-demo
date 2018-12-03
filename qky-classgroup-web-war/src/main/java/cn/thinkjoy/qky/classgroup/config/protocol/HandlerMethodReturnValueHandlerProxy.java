package cn.thinkjoy.qky.classgroup.config.protocol;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {
    private HandlerMethodReturnValueHandler proxyObject;

    public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "suc");
        resultMap.put("message", "获取成功");
        resultMap.put("data", returnValue);
        resultMap.put("time",new Date().getTime());
        proxyObject.handleReturnValue(resultMap, returnType, mavContainer, webRequest);
    }
}
