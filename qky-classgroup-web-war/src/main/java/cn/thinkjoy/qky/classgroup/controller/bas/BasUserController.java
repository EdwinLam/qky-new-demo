package cn.thinkjoy.qky.classgroup.controller.bas;

import cn.thinkjoy.qky.classgroup.controller.BaseController;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.service.bas.IBasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Edwin
 * @Date: 2018/11/27 16:28
 * @Description:
 */
@RestController
@RequestMapping({"/bas/user"})
public class BasUserController extends BaseController {
    @Autowired
    private IBasUserService basUserService;

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info")
    public BasUser info() {
        return getCurrUser();
    }

    /**
     * @param phone     手机
     * @param nickName  群组昵称
     * @param groupName 群组名称
     * @param introduce 群组介绍
     * @return
     */
    @RequestMapping(value = "/updateCurUserInfo")
    public boolean updateGroupInfo(String phone, String nickName, String groupName, String introduce) {
        if (basUserService.validateBaseInfo(phone, nickName, groupName, introduce)) {
            basUserService.updateBaseInfo(getCurrUser(), getCurrUser().getUserRel().getUserId(), phone, nickName, groupName, introduce);
        }
        return true;
    }

}
