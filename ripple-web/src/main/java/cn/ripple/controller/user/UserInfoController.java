package cn.ripple.controller.user;

import cn.ripple.controller.BaseController;
import cn.ripple.entity.user.UserInfo;
import cn.ripple.service.user.UserInfoService;
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
@RequestMapping("/ripple/userInfo")
public class UserInfoController extends BaseController<UserInfo, String>{

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserInfoService getService() {
        return userInfoService;
    }

}
