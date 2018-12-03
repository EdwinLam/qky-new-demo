package cn.thinkjoy.qky.classgroup.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.qky.classgroup.common.ErrorCodeEnum;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.config.protocol.Response;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @Auther: Edwin
 *  @Date: 2018/9/27 11:02
 *  @Description: 失败处理
 */
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

//    @Value("${ripple.loginTimeLimit}")
    private Integer loginTimeLimit = 10;

//    @Value("${ripple.loginAfterTime}")
    private Integer loginAfterTime =10;

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws ServletException {
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            String username = request.getParameter("username");
            recordLoginTime(username);
            String key = "loginTimeLimit:"+username;
//            String value = redisTemplate.opsForValue().get(key);
            String value = "";
            if(StrUtil.isBlank(value)){
                value = "0";
            }
            ResultUtils.outBizException(response,new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "用户名或密码错误"));
        } else {
            ResultUtils.outBizException(response,new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "登录失败"));
        }
    }

    /**
     * 判断用户登陆错误次数
     */
    public boolean recordLoginTime(String username){
        String key = "loginTimeLimit:"+username;
        String flagKey = "loginFailFlag:"+username;
//        String value = redisTemplate.opsForValue().get(key);
        String value = "";
        if(StrUtil.isBlank(value)){
            value = "0";
        }
        //获取已登录错误次数
        int loginFailTime = Integer.parseInt(value) + 1;
//        redisTemplate.opsForValue().set(key, String.valueOf(loginFailTime), loginAfterTime, TimeUnit.MINUTES);
//        if(loginFailTime>=loginTimeLimit){
//            redisTemplate.opsForValue().set(flagKey, "fail", loginAfterTime, TimeUnit.MINUTES);
//            return false;
//        }
        return true;
    }
}
