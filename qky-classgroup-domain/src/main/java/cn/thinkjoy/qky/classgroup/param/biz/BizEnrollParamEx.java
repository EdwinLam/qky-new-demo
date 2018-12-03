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

package cn.thinkjoy.qky.classgroup.param.biz;

import cn.thinkjoy.qky.classgroup.domain.biz.BizEnrollEx;

/**
 * 《报名主》 查询参数实体
 *
 * @author linyuewei
 */
public class BizEnrollParamEx extends BizEnrollParam {
    private static final long serialVersionUID = 1L;

    //针对BizEnrollParam实体在这里增加额外的属性和对应的get和set方法
    private BizEnrollEx bizEnroll;

    public BizEnrollEx getBizEnroll() {
        return bizEnroll;
    }

    public void setBizEnroll(BizEnrollEx bizEnroll) {
        this.bizEnroll = bizEnroll;
    }
}
