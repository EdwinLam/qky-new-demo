package cn.thinkjoy.qky.classgroup.config.security.jwt;


import cn.hutool.core.util.StrUtil;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.constant.SecurityConstant;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 登录成功处理类
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${qky.tokenExpireTime}")
    private Integer tokenExpireTime;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        //登陆成功生成JWT
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(username)
                //自定义属性 放入用户拥有权限
                .claim(SecurityConstant.AUTHORITIES, new Gson().toJson(new ArrayList<>()))
                .claim("userInfo", new Gson().toJson(authentication.getPrincipal()))
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();
        Map<String,Object> data = new HashMap<>();
        data.put("userInfo",authentication.getPrincipal());
        data.put("token",SecurityConstant.TOKEN_SPLIT + token);
        ResultUtils.outSuc(response,data);
    }
}
