/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：群组表
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

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.qky.classgroup.common.DataStatusEnum;
import cn.thinkjoy.qky.classgroup.common.ErrorCodeEnum;
import cn.thinkjoy.qky.classgroup.common.GroupNeeCheckEnum;
import cn.thinkjoy.qky.classgroup.common.MemberLimitEnum;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.bas.IBasGroupDAO;
import cn.thinkjoy.qky.classgroup.dao.code.ICodeIndexDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasGroup;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.code.CodeIndex;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import cn.thinkjoy.qky.classgroup.param.code.CodeIndexParam;
import cn.thinkjoy.qky.classgroup.param.user.UserRelParam;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import cn.thinkjoy.qky.classgroup.service.user.IUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《群组》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("BasGroupServiceImpl")
public class BasGroupServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<BasGroup>, BasGroup> implements IBasGroupService<IQkyBaseDAO<BasGroup>, BasGroup> {
    @Autowired
    private IBasGroupDAO basGroupDAO;
    @Autowired
    private ICodeIndexDAO iCodeIndexDAO;
    @Autowired
    private IUserRelService userRelService;

    @Override
    public IQkyBaseDAO<BasGroup> getDao() {
        return basGroupDAO;
    }

    /**
     * 添加分组
     *
     * @param basGroup
     * @return
     */
    @Transactional
    @Override
    public BasGroup addGroup(BasUser basUser, BasGroup basGroup) {
        basGroup.setGroupCode(getGroupCode());
        basGroup.setIsNeedCheck(GroupNeeCheckEnum.NO.getValue());
        basGroup.setMemberLimit(MemberLimitEnum.NO_LIMIT.getValue());
        ResultUtils.initCreateDomain(basGroup, basUser);
        basGroupDAO.insert(basGroup);
        return basGroup;
    }


    /**
     * 加入分组
     *
     * @param basUser
     * @param userRel
     */
    @Transactional
    @Override
    public void joinGroup(BasUser basUser, UserRel userRel) {
        ResultUtils.initCreateDomain(userRel, basUser);
        userRel.setStatus(DataStatusEnum.ENABLED.getValue());
        userRelService.insert(userRel);
        updateGroupMemberNum(userRel.getGroupId());
    }

    /**
     * 退出分组
     *
     * @param groupId
     * @param userId
     */
    @Transactional
    @Override
    public void exitGroup(Long groupId, Long userId) {
        UserRelParam userRelParam = new UserRelParam();
        userRelParam.setStatus(DataStatusEnum.ENABLED.getValue());
        userRelParam.setGroupId(groupId);
        userRelParam.setUserId(userId);
        UserRel userRel = (UserRel) userRelService.queryOne(userRelParam.toMap());
        if (userRel != null) {
            userRel.setStatus(DataStatusEnum.ENABLED.getValue());
            userRelService.update(userRel);
        }

    }

    /**
     * 更新分组成员数
     *
     * @param groupId
     */
    public void updateGroupMemberNum(Long groupId) {
        BasGroup basGroup = basGroupDAO.fetch(groupId);
        if (basGroup != null) {
            basGroup.setMemberNum(userRelService.countGroupRelUser(groupId));
            basGroupDAO.update(basGroup);
        }
    }


    /**
     * 验证是否能加入分组
     *
     * @param userRel
     * @return
     */
    @Override
    public boolean validateJoinGroup(UserRel userRel) {
        UserRelParam userRelParam = new UserRelParam();
        userRelParam.setGroupId(userRel.getGroupId());
        userRelParam.setUserId(userRel.getUserId());
        UserRel dbUserRel = (UserRel) userRelService.queryOne(userRelParam.toMap());
        BasGroup basGroup = basGroupDAO.fetch(userRel.getGroupId());
        if (userRel.getGroupId() == null || basGroup == null || basGroup.getStatus() == DataStatusEnum.DISABLED.getValue())
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "不存在加入分组");
        if (dbUserRel == null)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "已存在班级，不能重复加入");
        return true;
    }

    /**
     * 是否存在分组
     *
     * @param groupId
     * @return
     */
    @Override
    public boolean isExistGroup(Long groupId) {
        if (groupId == null) return false;
        BasGroup basGroup = basGroupDAO.fetch(groupId);
        return basGroup == null || basGroup.getStatus() == DataStatusEnum.DISABLED.getValue();
    }

    /**
     * 验证分组
     *
     * @param basGroup
     * @return
     */
    @Override
    public boolean validateGroup(BasGroup basGroup) {
        if (basGroup.getGroupName().length() > 10) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), ErrorCodeEnum.PARAM_ERROR.getMessage());
        }
        // TODO 验证手机验证码
        return true;
    }

    /**
     * 获取groupCode
     *
     * @return
     */
    @Transactional
    public String getGroupCode() {
        CodeIndexParam codeIndexParam = new CodeIndexParam();
        codeIndexParam.setStatus(DataStatusEnum.ENABLED.getValue());
        codeIndexParam.setDigit(6);//获取6位code
        CodeIndex codeIndex = iCodeIndexDAO.queryOne(codeIndexParam.toMap(), CodeIndexParam.F_Code, SqlOrderEnum.ASC.getAction());
        if (codeIndex != null)
            codeIndex.setStatus(DataStatusEnum.DISABLED.getValue());
        iCodeIndexDAO.update(codeIndex);
        return codeIndex.getCode();
    }
}
