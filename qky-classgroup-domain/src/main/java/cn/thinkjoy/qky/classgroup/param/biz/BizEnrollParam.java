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

import cn.thinkjoy.qky.classgroup.param.CreateBaseParam;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《报名主》 查询参数实体
 *
 * @author linyuewei
 */
public class BizEnrollParam extends CreateBaseParam<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段常量——所属群组id
     */
    public static final String F_GroupId = "groupId";
    /**
     * 字段常量——标题
     */
    public static final String F_Title = "title";
    /**
     * 字段常量——内容
     */
    public static final String F_Content = "content";
    /**
     * 字段常量——报名截止时间
     */
    public static final String F_EndDate = "endDate";
    /**
     * 字段常量——已读人数
     */
    public static final String F_ReadNum = "readNum";
    /**
     * 字段常量——报名人数
     */
    public static final String F_EnrollNum = "enrollNum";
    /**
     * 字段常量——报名名额,-1表示不限；
     */
    public static final String F_LimitNum = "limitNum";
    /**
     * 字段常量——创建人名称
     */
    public static final String F_CreatorName = "creatorName";

    private Long groupId; //所属群组id
    private String title; //标题
    private String content; //内容
    private Long endDate; //报名截止时间
    private Integer readNum; //已读人数
    private Integer enrollNum; //报名人数
    private Integer limitNum; //报名名额,-1表示不限；
    private String creatorName; //创建人名称

    /**
     * 默认空构造函数
     */
    public BizEnrollParam() {
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
     * @return endDate 报名截止时间
     */
    public Long getEndDate() {
        return this.endDate;
    }

    /**
     * @param endDate 报名截止时间
     */
    public void setEndDate(Long endDate) {
        this.endDate = endDate;
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
     * @return enrollNum 报名人数
     */
    public Integer getEnrollNum() {
        return this.enrollNum;
    }

    /**
     * @param enrollNum 报名人数
     */
    public void setEnrollNum(Integer enrollNum) {
        this.enrollNum = enrollNum;
    }

    /**
     * @return limitNum 报名名额,-1表示不限；
     */
    public Integer getLimitNum() {
        return this.limitNum;
    }

    /**
     * @param limitNum 报名名额,-1表示不限；
     */
    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
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
                .append("endDate", getEndDate())
                .append("readNum", getReadNum())
                .append("enrollNum", getEnrollNum())
                .append("limitNum", getLimitNum())
                .append("creatorName", getCreatorName())
                .append("creator", getCreator())
                .append("createDate", getCreateDate())
                .append("lastModifier", getLastModifier())
                .append("lastModDate", getLastModDate())
                .append("status", getStatus())
                .toString();
    }

}
