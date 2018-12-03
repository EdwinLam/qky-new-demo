/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：报名主表
{  功能描述:
{
{  ---------------------------------------------------------------------------
{  维护历史:
{  日期        维护人        维护类型
{  ---------------------------------------------------------------------------
{  2018-11-29  linyuewei        新建
{
{  ---------------------------------------------------------------------------
{  注：本模块代码由codgen代码生成工具辅助生成 http://www.oschina.net/p/codgen
{*****************************************************************************
*/

package cn.thinkjoy.qky.classgroup.service.biz;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.biz.BizEnroll;
import cn.thinkjoy.qky.classgroup.domain.biz.BizEnrollEx;
import cn.thinkjoy.qky.classgroup.service.IQkyBaseService;

import java.lang.reflect.InvocationTargetException;

/**
 * 《报名主》 业务逻辑服务接口
 *
 * @author linyuewei
 */
public interface IBizEnrollService<D extends IQkyBaseDAO<T>, T extends BaseDomain> extends IQkyBaseService<D, T>, IPageService<D, T> {
    /**
     * 添加报名
     *
     * @param curUser
     * @param bizEnroll
     * @return
     */
    BizEnrollEx addEnroll(BasUser curUser, BizEnrollEx bizEnroll);

    /**
     * 标记报名已读
     *
     * @param curUser
     * @param id
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void readEnroll(BasUser curUser, long id) throws InvocationTargetException, IllegalAccessException;

    /**
     * 根据id查看报名
     *
     * @param id
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    BizEnrollEx viewEnroll(long id) throws InvocationTargetException, IllegalAccessException;

    /**
     * 添加报名项目验证
     *
     * @param bizEnroll
     * @return
     */
    boolean validateAddEnroll(BizEnrollEx bizEnroll);

    /**
     * 报名验证
     *
     * @param basUser
     * @param enrollId
     * @return
     */
    boolean validateDoEnroll(BasUser basUser, Long enrollId);

    /**
     * 报名
     *
     * @param curUser
     * @param enrollId
     * @param remark
     */
    void enroll(BasUser curUser, Long enrollId, String remark);

    /**
     * 获取报名状态
     *
     * @param userId
     * @param bizEnroll
     * @return
     */
    int getEnrollStatus(Long userId, BizEnroll bizEnroll);
}
