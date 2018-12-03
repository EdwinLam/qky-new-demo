/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：消息总表
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

import cn.thinkjoy.qky.classgroup.domain.QkyCreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《消息总表》 实体
 *
 * @author linyuewei
 */
public class BizMessage extends QkyCreateBaseDomain<Long> {
    private static final long serialVersionUID = 1L;

    private Long groupId; //所属群组id
    private Long sourceId; //所属业务表id
    private String sourceCode; //来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
    private String messageTitle; //消息名称
    private String messageContent; //消息内容
    private String creatorName; //创建者名称

    /**
     * 默认空构造函数
     */
    public BizMessage() {
        super();
    }

    /**
     * @return groupId 所属群组id
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * @param groupId 所属群组id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return sourceId 所属业务表id
     */
    public Long getSourceId() {
        return this.sourceId;
    }

    /**
     * @param sourceId 所属业务表id
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return sourceCode 来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
     */
    public String getSourceCode() {
        return this.sourceCode;
    }

    /**
     * @param sourceCode 来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * @return messageTitle 消息名称
     */
    public String getMessageTitle() {
        return this.messageTitle;
    }

    /**
     * @param messageTitle 消息名称
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    /**
     * @return messageContent 消息内容
     */
    public String getMessageContent() {
        return this.messageContent;
    }

    /**
     * @param messageContent 消息内容
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * @return creatorName 创建者名称
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * @param creatorName 创建者名称
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("sourceId", getSourceId())
                .append("sourceCode", getSourceCode())
                .append("messageTitle", getMessageTitle())
                .append("messageContent", getMessageContent())
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
                .append(getSourceId())
                .append(getSourceCode())
                .append(getMessageTitle())
                .append(getMessageContent())
                .append(getCreatorName())
                .append(getCreator())
                .append(getCreateDate())
                .append(getLastModifier())
                .append(getLastModDate())
                .append(getStatus())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof BizMessage == false) return false;
        if (this == obj) return true;
        BizMessage other = (BizMessage) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}
