package cn.thinkjoy.qky.classgroup.config.exception;


import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.qky.classgroup.config.protocol.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 处理Rest接口请求时的异常
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Map<String, Object> restError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Response((BizException) ex).toMap();
    }
}
