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

package cn.thinkjoy.qky.classgroup.domain.biz;

import cn.thinkjoy.qky.classgroup.domain.bas.BasAtts;
import cn.thinkjoy.qky.classgroup.domain.enroll.EnrollUser;

import java.util.List;

/**
 * 《报名主》扩展实体
 *
 * @author linyuewei
 */
public class BizEnrollEx extends BizEnroll {
    private static final long serialVersionUID = 1L;

    //针对BizEnroll实体在这里增加额外的属性和对应的get和set方法
    /**
     * 图片附件列表
     */
    private List<BasAtts> basAtts;
    /**
     * 报名人数
     */
    private List<EnrollUser> enrollUsers;
    /**
     * 报名状态 0-未报名 1-已报名 2-报名已截止 3-报名名额已满
     */
    private int enrollStatus;

    public List<BasAtts> getBasAtts() {
        return basAtts;
    }

    public void setBasAtts(List<BasAtts> basAtts) {
        this.basAtts = basAtts;
    }

    public List<EnrollUser> getEnrollUsers() {
        return enrollUsers;
    }

    public void setEnrollUsers(List<EnrollUser> enrollUsers) {
        this.enrollUsers = enrollUsers;
    }

    public int getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(int enrollStatus) {
        this.enrollStatus = enrollStatus;
    }
}
