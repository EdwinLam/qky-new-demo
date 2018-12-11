package cn.ripple.controller;

import cn.ripple.entity.User;
import cn.ripple.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "用户管理接口")
@RequestMapping("/ripple/user")
public class UserController extends BaseController<User, String>{

    @Autowired
    private UserService userService;

    @Override
    public UserService getService() {
        return userService;
    }

}
