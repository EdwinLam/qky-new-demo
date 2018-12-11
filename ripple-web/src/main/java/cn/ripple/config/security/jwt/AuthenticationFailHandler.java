package cn.ripple.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import cn.ripple.enums.ResponseCodeEnum;
import cn.ripple.exception.RippleException;
import cn.ripple.utils.ResponseUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
            Map<String,Object> result = new HashMap<>();
            result.put("code",ResponseCodeEnum.BUSINESS_ERROR.getValue());
            result.put("message", "用户名或密码错误");
            ResponseUtil.out(response,result);
        } else {
            Map<String,Object> result = new HashMap<>();
            result.put("code",ResponseCodeEnum.BUSINESS_ERROR.getValue());
            result.put("message", "登录失败");
            ResponseUtil.out(response,result);
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
