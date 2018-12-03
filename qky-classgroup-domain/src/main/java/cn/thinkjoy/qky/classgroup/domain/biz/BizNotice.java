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
{  2018-11-28  linyuewei        新建
{
{  ---------------------------------------------------------------------------
{  注：本模块代码由codgen代码生成工具辅助生成 http://www.oschina.net/p/codgen
{*****************************************************************************
*/

package cn.thinkjoy.qky.classgroup.domain.biz;

import cn.thinkjoy.qky.classgroup.domain.QkyCreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《通知》 实体
 *
 * @author linyuewei
 */
public class BizNotice extends QkyCreateBaseDomain<Long> {
    private static final long serialVersionUID = 1L;

    private Long groupId; //所属群组
    private String title; //标题
    private String content; //内容
    private Integer readNum; //已读人数
    private String creatorName; //创建人名称

    /**
     * 默认空构造函数
     */
    public BizNotice() {
        super();
    }

    /**
     * @return groupId 所属群组
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * @param groupId 所属群组
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return title 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return content 内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return readNum 已读人数
     */
    public Integer getReadNum() {
        return this.readNum;
    }

    /**
     * @param readNum 已读人数
     */
    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    /**
     * @return creatorName 创建人名称
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * @param creatorName 创建人名称
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("title", getTitle())
                .append("content", getContent())
                .append("readNum", getReadNum())
                .append("creatorName", getCreatorName())
                .append("creator", getCreator())
                .append("createDate", getCreateDate())
                .append("lastModifier", getLastModifier())
                .append("lastModDate", getLastModDate())
                .append("status", getStatus())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .append(getGroupId())
                .append(getTitle())
                .append(getContent())
                .append(getReadNum())
                .append(getCreatorName())
                .append(getCreator())
                .append(getCreateDate())
                .append(getLastModifier())
                .append(getLastModDate())
                .append(getStatus())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof BizNotice == false) return false;
        if (this == obj) return true;
        BizNotice other = (BizNotice) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}
