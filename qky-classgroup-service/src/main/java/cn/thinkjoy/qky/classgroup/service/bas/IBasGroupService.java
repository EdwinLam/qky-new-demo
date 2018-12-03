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

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasGroup;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import cn.thinkjoy.qky.classgroup.service.IQkyBaseService;

/**
 * 《群组》 业务逻辑服务接口
 *
 * @author linyuewei
 */
public interface IBasGroupService<D extends IQkyBaseDAO<T>, T extends BaseDomain> extends IQkyBaseService<D, T>, IPageService<D, T> {
    /**
     * 增加群组
     *
     * @param basGroup
     * @return
     */
    BasGroup addGroup(BasUser basUser, BasGroup basGroup);

    /**
     * 加入分组
     *
     * @param basUser
     * @param userRel
     */
    void joinGroup(BasUser basUser, UserRel userRel);

    /**
     * 验证群组
     *
     * @param basGroup
     * @return
     */
    boolean validateGroup(BasGroup basGroup);

    /**
     * 验证是否能加入分组
     *
     * @param userRel
     * @return
     */
    boolean validateJoinGroup(UserRel userRel);

    /**
     * 是否存在分组
     *
     * @param groupId
     * @return
     */
    boolean isExistGroup(Long groupId);

    /**
     * 退出分组
     *
     * @param groupId
     * @param userId
     */
    void exitGroup(Long groupId, Long userId);
}
