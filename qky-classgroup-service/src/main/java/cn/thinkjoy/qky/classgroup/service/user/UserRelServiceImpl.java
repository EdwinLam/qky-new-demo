/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：群组与用户关系表，一个用户可以有N个群组
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

package cn.thinkjoy.qky.classgroup.service.user;

import cn.thinkjoy.qky.classgroup.common.DataStatusEnum;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.user.IUserRelDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import cn.thinkjoy.qky.classgroup.param.user.UserRelParam;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 《群组与用户关系表，一个用户可以有N个群组》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("UserRelServiceImpl")
public class UserRelServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<UserRel>, UserRel> implements IUserRelService<IQkyBaseDAO<UserRel>, UserRel> {
    @Autowired
    private IUserRelDAO userRelDAO;

    @Override
    public IQkyBaseDAO<UserRel> getDao() {
        return userRelDAO;
    }

    /**
     * 获取分组相关的用户
     *
     * @param groupId
     * @return
     */
    @Override
    public List<BasUserEx> fetchGroupRelUser(Long groupId) {
        return userRelDAO.fetchGroupRelUser(groupId);
    }

    /**
     * 统计分组成员数
     *
     * @param groupId
     * @return
     */
    public int countGroupRelUser(Long groupId) {
        UserRelParam userRelParam = new UserRelParam();
        userRelParam.setStatus(DataStatusEnum.ENABLED.getValue());
        userRelParam.setGroupId(groupId);
        return userRelDAO.count(userRelParam.toMap());
    }

}
