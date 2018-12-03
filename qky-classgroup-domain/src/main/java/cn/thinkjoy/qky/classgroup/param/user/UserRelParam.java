/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：群组与用户关系表，一个用户可以有N个群组
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

package cn.thinkjoy.qky.classgroup.param.user;

import cn.thinkjoy.qky.classgroup.param.CreateBaseParam;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《群组与用户关系表，一个用户可以有N个群组》 查询参数实体
 *
 * @author linyuewei
 */
public class UserRelParam extends CreateBaseParam<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段常量——群组id
     */
    public static final String F_GroupId = "groupId";
    /**
     * 字段常量——用户id
     */
    public static final String F_UserId = "userId";
    /**
     * 字段常量——用户类型(1:教师；2：家长)
     */
    public static final String F_UserType = "userType";
    /**
     * 字段常量——用户在群组中的身份，1：群主（创建人) ；2：管理员；3：普通用户。群组创建人默认是群主，管理员由群主设置。
     */
    public static final String F_UserIdentity = "userIdentity";
    /**
     * 字段常量——成员状态，审核不通过时，移除记录，只保留两种状态；0：待审核，1：正式成员；
     */
    public static final String F_UserStatus = "userStatus";
    /**
     * 字段常量——成员在群组中的昵称
     */
    public static final String F_NickName = "nickName";
    /**
     * 字段常量——备注，可用于用户申请加入群组时，填写的信息，用于审核依据
     */
    public static final String F_Remark = "remark";

    private Long groupId; //群组id
    private Long userId; //用户id
    private Integer userType; //用户类型(1:教师；2：家长)
    private Integer userIdentity; //用户在群组中的身份，1：群主（创建人) ；2：管理员；3：普通用户。群组创建人默认是群主，管理员由群主设置。
    private Integer userStatus; //成员状态，审核不通过时，移除记录，只保留两种状态；0：待审核，1：正式成员；
    private String nickName; //成员在群组中的昵称
    private String remark; //备注，可用于用户申请加入群组时，填写的信息，用于审核依据

    /**
     * 默认空构造函数
     */
    public UserRelParam() {
        super();
    }

    /**
     * @return groupId 群组id
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * @param groupId 群组id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return userId 用户id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return userType 用户类型(1:教师；2：家长)
     */
    public Integer getUserType() {
        return this.userType;
    }

    /**
     * @param userType 用户类型(1:教师；2：家长)
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * @return userIdentity 用户在群组中的身份，1：群主（创建人) ；2：管理员；3：普通用户。群组创建人默认是群主，管理员由群主设置。
     */
    public Integer getUserIdentity() {
        return this.userIdentity;
    }

    /**
     * @param userIdentity 用户在群组中的身份，1：群主（创建人) ；2：管理员；3：普通用户。群组创建人默认是群主，管理员由群主设置。
     */
    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    /**
     * @return userStatus 成员状态，审核不通过时，移除记录，只保留两种状态；0：待审核，1：正式成员；
     */
    public Integer getUserStatus() {
        return this.userStatus;
    }

    /**
     * @param userStatus 成员状态，审核不通过时，移除记录，只保留两种状态；0：待审核，1：正式成员；
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * @return nickName 成员在群组中的昵称
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * @param nickName 成员在群组中的昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return remark 备注，可用于用户申请加入群组时，填写的信息，用于审核依据
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * @param remark 备注，可用于用户申请加入群组时，填写的信息，用于审核依据
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("userId", getUserId())
                .append("userType", getUserType())
                .append("userIdentity", getUserIdentity())
                .append("userStatus", getUserStatus())
                .append("nickName", getNickName())
                .append("remark", getRemark())
                .append("creator", getCreator())
                .append("createDate", getCreateDate())
                .append("lastModifier", getLastModifier())
                .append("lastModDate", getLastModDate())
                .append("status", getStatus())
                .toString();
    }

}
