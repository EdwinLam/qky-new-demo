/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：通知接收情况表
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

package cn.thinkjoy.qky.classgroup.param.notice;

import cn.thinkjoy.qky.classgroup.param.CreateBaseParam;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《通知接收情况》 查询参数实体
 *
 * @author linyuewei
 */
public class NoticeReceiveParam extends CreateBaseParam<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段常量——分组id
     */
    public static final String F_GroupId = "groupId";
    /**
     * 字段常量——通知的id
     */
    public static final String F_NoticeId = "noticeId";
    /**
     * 字段常量——接收人id ，用户表中的用户id
     */
    public static final String F_UserId = "userId";
    /**
     * 字段常量——接收人在群昵称（冗余字段）
     */
    public static final String F_NickName = "nickName";
    /**
     * 字段常量——阅读状态（0：未读；1：已读)
     */
    public static final String F_ReadStatus = "readStatus";
    /**
     * 字段常量——阅读时间
     */
    public static final String F_ReadDate = "readDate";

    private Long groupId; //分组id
    private Long noticeId; //通知的id
    private Long userId; //接收人id ，用户表中的用户id
    private String nickName; //接收人在群昵称（冗余字段）
    private Integer readStatus; //阅读状态（0：未读；1：已读)
    private Long readDate; //阅读时间

    /**
     * 默认空构造函数
     */
    public NoticeReceiveParam() {
        super();
    }

    /**
     * @return groupId 分组id
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * @param groupId 分组id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return noticeId 通知的id
     */
    public Long getNoticeId() {
        return this.noticeId;
    }

    /**
     * @param noticeId 通知的id
     */
    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * @return userId 接收人id ，用户表中的用户id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * @param userId 接收人id ，用户表中的用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return nickName 接收人在群昵称（冗余字段）
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * @param nickName 接收人在群昵称（冗余字段）
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return readStatus 阅读状态（0：未读；1：已读)
     */
    public Integer getReadStatus() {
        return this.readStatus;
    }

    /**
     * @param readStatus 阅读状态（0：未读；1：已读)
     */
    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    /**
     * @return readDate 阅读时间
     */
    public Long getReadDate() {
        return this.readDate;
    }

    /**
     * @param readDate 阅读时间
     */
    public void setReadDate(Long readDate) {
        this.readDate = readDate;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("noticeId", getNoticeId())
                .append("userId", getUserId())
                .append("nickName", getNickName())
                .append("readStatus", getReadStatus())
                .append("readDate", getReadDate())
                .append("creator", getCreator())
                .append("createDate", getCreateDate())
                .append("lastModifier", getLastModifier())
                .append("lastModDate", getLastModDate())
                .append("status", getStatus())
                .toString();
    }

}
