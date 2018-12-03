/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：通知表
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

package cn.thinkjoy.qky.classgroup.service.biz;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.biz.BizNoticeEx;
import cn.thinkjoy.qky.classgroup.service.IQkyBaseService;

import java.lang.reflect.InvocationTargetException;

/**
 * 《通知》 业务逻辑服务接口
 *
 * @author linyuewei
 */
public interface IBizNoticeService<D extends IQkyBaseDAO<T>, T extends BaseDomain> extends IQkyBaseService<D, T>, IPageService<D, T> {
    /**
     * 增加通知
     *
     * @param basUser
     * @param bizNotice
     * @return
     */
    BizNoticeEx addNotice(BasUser basUser, BizNoticeEx bizNotice);

    /**
     * 增加通知验证
     *
     * @param bizNotice
     * @return
     */
    boolean validateAddNotice(BizNoticeEx bizNotice);

    /**
     * 补充接收人列表
     *
     * @param curUser
     * @param groupId
     */
    void fillNoticeReceive(BasUser curUser, Long groupId, Long noticeId);

    /**
     * 获取通知详情
     *
     * @param id
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    BizNoticeEx viewNotice(long id) throws InvocationTargetException, IllegalAccessException;

    /**
     * 标记通知已读
     *
     * @param curUser
     * @param id
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void readNotice(BasUser curUser, long id) throws InvocationTargetException, IllegalAccessException;
}
