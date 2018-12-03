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

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.service.IQkyBaseService;

/**
 * 《用户基本信息》 业务逻辑服务接口
 *
 * @author linyuewei
 */
public interface IBasUserService<D extends IQkyBaseDAO<T>, T extends BaseDomain> extends IQkyBaseService<D, T>, IPageService<D, T> {
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
    boolean updateBaseInfo(BasUser curUser, Long relUserId, String phone, String nickName, String groupName, String introduce);

    /**
     * 验证基础信息
     *
     * @param phone     号码
     * @param nickName  群里面的昵称
     * @param groupName 分组名称
     * @param introduce 分组介绍
     * @return
     */
    boolean validateBaseInfo(String phone, String nickName, String groupName, String introduce);
}
