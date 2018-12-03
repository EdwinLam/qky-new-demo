package cn.thinkjoy.qky.classgroup.controller.bas;

import cn.hutool.core.util.StrUtil;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.qky.classgroup.common.DataStatusEnum;
import cn.thinkjoy.qky.classgroup.common.ErrorCodeEnum;
import cn.thinkjoy.qky.classgroup.common.GroupUserIdentityEnum;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.controller.BaseController;
import cn.thinkjoy.qky.classgroup.domain.bas.BasGroup;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import cn.thinkjoy.qky.classgroup.service.bas.IBasGroupService;
import cn.thinkjoy.qky.classgroup.service.user.IUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Edwin
 * @Date: 2018/11/27 16:28
 * @Description:
 */
@RestController
@RequestMapping({"/bas/group"})
public class BasGroupController extends BaseController {
    @Autowired
    private IBasGroupService basGroupService;
    @Autowired
    private IUserRelService userRelService;

    /**
     * 添加分组
     *
     * @param basGroup
     * @return
     */
    @RequestMapping(value = "/add")
    public BasGroup add(BasGroup basGroup) {
        if (basGroupService.validateGroup(basGroup)) {
            basGroupService.addGroup(getCurrUser(), basGroup);
        }
        return basGroup;
    }

    /**
     * 加入分组
     *
     * @param userRel
     * @return
     */
    @RequestMapping(value = "/join")
    public boolean join(UserRel userRel) {
        if (basGroupService.validateJoinGroup(userRel)) {
            basGroupService.joinGroup(getCurrUser(), userRel);
        }
        return true;
    }


    /**
     * 退出分组
     *
     * @param id     分组id
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/exit/{id}/{userId}")
    public boolean exit(@PathVariable long id, @PathVariable long userId) {
        basGroupService.exitGroup(id, userId);
        return true;
    }

    /**
     * 根据id获取分组
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}")
    public BasGroup view(@PathVariable Long id) {
        BasGroup basGroup = (BasGroup) basGroupService.fetch(id);
        if (basGroup == null || basGroup.getStatus() != DataStatusEnum.ENABLED.getValue())
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "不存在分组记录");
        return basGroup;
    }

    /**
     * 根据id删除分组
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}")
    public boolean del(@PathVariable long id) {
        BasGroup basGroup = (BasGroup) basGroupService.fetch(id);
        basGroup.setStatus(DataStatusEnum.DISABLED.getValue());
        basGroupService.update(basGroup);
        return true;
    }

    /**
     * 获取分组成员列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/member")
    public List<UserRel> member(@PathVariable long id) {
        return userRelService.fetchGroupRelUser(id);
    }

    /**
     * 更新用户所在分组昵称
     *
     * @param userRelId
     * @param nickName
     * @return
     */
    @RequestMapping(value = "/updateGroupNickName")
    public boolean updateGroupNickName(@PathVariable Long userRelId, String nickName) {
        if (StrUtil.isBlank(nickName) || nickName.length() > 10) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "姓名不能为空且限10个字以内");
        }
        UserRel userRel = (UserRel) userRelService.fetch(userRelId);
        if (userRel != null) {
            ResultUtils.initUpdateDomain(userRel, getCurrUser());
            userRel.setNickName(nickName);
        }
        return true;
    }

    /**
     * 设置用户管理员
     *
     * @param userRelId
     * @param isAdmin
     * @return
     */
    @RequestMapping(value = "/setAdmin/{userRelId}")
    public boolean setAdmin(@PathVariable Long userRelId, boolean isAdmin) {
        if (!getCurrUser().isAdmin() && !getCurrUser().isGroupOwner()) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "没有权限操作用户权限变更");
        }
        UserRel userRel = (UserRel) userRelService.fetch(userRelId);
        if (userRel != null && userRel.getUserIdentity() != GroupUserIdentityEnum.GROUP_OWNER.getValue()) {
            ResultUtils.initUpdateDomain(userRel, getCurrUser());
            userRel.setUserIdentity(isAdmin ? GroupUserIdentityEnum.ADMIN.getValue() : GroupUserIdentityEnum.NORMAL_USER.getValue());
        }
        return true;
    }

}
