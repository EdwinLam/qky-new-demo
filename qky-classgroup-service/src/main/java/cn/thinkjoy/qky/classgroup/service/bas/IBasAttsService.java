/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：附件表，有于记录整个应用中各个功能模块产生的附件
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
import cn.thinkjoy.qky.classgroup.domain.bas.BasAtts;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.service.IQkyBaseService;

import java.util.List;

/**
 * 《附件表，有于记录整个应用中各个功能模块产生的附件》 业务逻辑服务接口
 *
 * @author linyuewei
 */
public interface IBasAttsService<D extends IQkyBaseDAO<T>, T extends BaseDomain> extends IQkyBaseService<D, T>, IPageService<D, T> {
    /**
     * 批量添加通知附件
     *
     * @param basUser
     * @param bizNotice
     * @param basAtts
     * @return
     */
    boolean batchAddAttr(String sourceCode, long sourceId, BasUser basUser, List<BasAtts> basAtts);
}
