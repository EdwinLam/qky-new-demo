/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：用户基本信息
{  功能描述:
{
{  ---------------------------------------------------------------------------
{  维护历史:
{  日期        维护人        维护类型
{  ---------------------------------------------------------------------------
{  2018-11-27  linyuewei        新建
{
{  ---------------------------------------------------------------------------
{  注：本模块代码由codgen代码生成工具辅助生成 http://www.oschina.net/p/codgen
{*****************************************************************************
*/

package cn.thinkjoy.qky.classgroup.service.bas;

import cn.hutool.core.util.StrUtil;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.qky.classgroup.common.ErrorCodeEnum;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.bas.IBasUserDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasGroup;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import cn.thinkjoy.qky.classgroup.service.user.IUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 《用户基本信息》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("BasUserServiceImpl")
public class BasUserServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<BasUser>, BasUser> implements IBasUserService<IQkyBaseDAO<BasUser>, BasUser> {
    @Autowired
    private IBasUserDAO basUserDAO;
    @Autowired
    private IBasGroupService basGroupService;
    @Autowired
    private IUserRelService userRelService;

    @Override
    public IQkyBaseDAO<BasUser> getDao() {
        return basUserDAO;
    }

    /**
     * 更新基础信息
     *
     * @param curUser   当前登录用户
     * @param relUserId 分组用户关系id
     * @param phone     号码
     * @param nickName  昵称
     * @param groupName 分组名称
     * @param introduce 介绍
     * @return
     */
    @Override
    public boolean updateBaseInfo(BasUser curUser, Long relUserId, String phone, String nickName, String groupName, String introduce) {
        UserRel userRel = (UserRel) userRelService.fetch(relUserId);
        if (userRel != null) return false;
        // 获取用户信息
        BasUser basUser = basUserDAO.fetch(userRel.getUserId());
        if (basUser != null) {
            ResultUtils.initUpdateDomain(curUser, basUser);
            basUser.setPhoneNumber(phone);
            basUser.setNickName(nickName);
            basUserDAO.update(basUser);
        }
        // 获取分组信息 管理员和群主才可以修改
        BasGroup basGroup = (BasGroup) basGroupService.fetch(userRel.getGroupId());
        if (basGroup != null) {
            basGroup.setGroupName(groupName);
            basGroup.setIntroduce(introduce);
            ResultUtils.initUpdateDomain(basGroup, curUser);
            basGroupService.update(basGroup);
        }
        userRel.setNickName(nickName);
        ResultUtils.initUpdateDomain(userRel, basUser);
        userRelService.update(userRel);
        return true;
    }


    /**
     * 验证基础信息
     *
     * @param phone     号码
     * @param nickName  群里面的昵称
     * @param groupName 分组名称
     * @param introduce 分组介绍
     * @return
     */
    @Override
    public boolean validateBaseInfo(String phone, String nickName, String groupName, String introduce) {
        if (StrUtil.isBlank(phone))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "手机号码不能为空");
        if (StrUtil.isBlank(nickName)){
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "用户姓名不能为空");
        }
        if (nickName.length() > 10)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "用户姓名限10字以内");
        if (StrUtil.isBlank(groupName))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "班级不能为空");
        if (introduce != null && introduce.length() > 100)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "班级公告限100字");
        return true;
    }

}
