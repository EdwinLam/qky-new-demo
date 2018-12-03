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

package cn.thinkjoy.qky.classgroup.param.biz;

import cn.thinkjoy.qky.classgroup.domain.biz.BizNoticeEx;

/**
 * 《通知》 查询参数实体
 *
 * @author linyuewei
 */
public class BizNoticeParamEx extends BizNoticeParam {
    private static final long serialVersionUID = 1L;

    //针对BizNoticeParam实体在这里增加额外的属性和对应的get和set方法
    private BizNoticeEx bizNotice;

    public BizNoticeEx getBizNotice() {
        return bizNotice;
    }

    public void setBizNotice(BizNoticeEx bizNotice) {
        this.bizNotice = bizNotice;
    }
}
