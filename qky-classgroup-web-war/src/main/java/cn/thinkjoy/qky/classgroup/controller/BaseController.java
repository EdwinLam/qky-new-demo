package cn.thinkjoy.qky.classgroup.controller;

import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;

/**
 * Created by tengen on 2016/2/3.
 */
public abstract class BaseController {

    @Autowired
    private HttpSession session;

    /**
     * 获取当前用户信息
     * @return
     */
    protected BasUserEx getCurrUser(){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (BasUserEx)usernamePasswordAuthenticationToken.getPrincipal();
    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    protected Long getCurrUserId() {
        return getCurrUser().getId();
    }


}
