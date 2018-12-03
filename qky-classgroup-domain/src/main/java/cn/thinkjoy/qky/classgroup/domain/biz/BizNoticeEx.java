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

package cn.thinkjoy.qky.classgroup.domain.biz;

import cn.thinkjoy.qky.classgroup.domain.bas.BasAtts;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;

import java.util.List;

/**
 * 《通知》扩展实体
 *
 * @author linyuewei
 */
public class BizNoticeEx extends BizNotice {
    private static final long serialVersionUID = 1L;

    //针对BizNotice实体在这里增加额外的属性和对应的get和set方法
    private boolean read;
    /**
     * 创建人名称
     */
    private String creatorName;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 图片附件列表
     */
    private List<BasAtts> basAtts;

    /**
     * 已读用户列表
     */
    private List<BasUser> readUsers;

    /**
     * 未读用户列表
     */
    private List<BasUser> unReadUsers;

    public List<BasAtts> getBasAtts() {
        return basAtts;
    }

    public void setBasAtts(List<BasAtts> basAtts) {
        this.basAtts = basAtts;
    }

    public List<BasUser> getReadUsers() {
        return readUsers;
    }

    public void setReadUsers(List<BasUser> readUsers) {
        this.readUsers = readUsers;
    }

    public List<BasUser> getUnReadUsers() {
        return unReadUsers;
    }

    public void setUnReadUsers(List<BasUser> unReadUsers) {
        this.unReadUsers = unReadUsers;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
