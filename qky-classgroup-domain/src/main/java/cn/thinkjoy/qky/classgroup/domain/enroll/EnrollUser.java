/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：活动报名名单表，类似接龙游戏
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

package cn.thinkjoy.qky.classgroup.domain.enroll;

import cn.thinkjoy.qky.classgroup.domain.QkyCreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《活动报名名单表，类似接龙游戏》 实体
 *
 * @author linyuewei
 */
public class EnrollUser extends QkyCreateBaseDomain<Long> {
    private static final long serialVersionUID = 1L;

    private Long groupId; //分组id
    private Long enrollId; //报名id
    private Long userId; //用户id
    private String nickName; //用户昵称
    private String remark; //备注

    /**
     * 默认空构造函数
     */
    public EnrollUser() {
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
     * @return enrollId 报名id
     */
    public Long getEnrollId() {
        return this.enrollId;
    }

    /**
     * @param enrollId 报名id
     */
    public void setEnrollId(Long enrollId) {
        this.enrollId = enrollId;
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
     * @return nickName 用户昵称
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return remark 备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("enrollId", getEnrollId())
                .append("userId", getUserId())
                .append("nickName", getNickName())
                .append("remark", getRemark())
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
                .append(getEnrollId())
                .append(getUserId())
                .append(getNickName())
                .append(getRemark())
                .append(getCreator())
                .append(getCreateDate())
                .append(getLastModifier())
                .append(getLastModDate())
                .append(getStatus())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof EnrollUser == false) return false;
        if (this == obj) return true;
        EnrollUser other = (EnrollUser) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}
