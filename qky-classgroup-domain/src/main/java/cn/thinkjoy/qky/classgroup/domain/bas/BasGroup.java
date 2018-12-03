/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：群组表
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

package cn.thinkjoy.qky.classgroup.domain.bas;

import cn.thinkjoy.qky.classgroup.domain.QkyCreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《群组》 实体
 *
 * @author linyuewei
 */
public class BasGroup extends QkyCreateBaseDomain<Long> {
    private static final long serialVersionUID = 1L;

    private String groupCode; //群号,9位数字构成,类似于QQ群的群号
    private String groupName; //群组名称
    private String introduce; //群组介绍
    private Integer memberLimit; //成员人数限制,-1表示无限制（该字段用于日后增值考虑）
    private Integer isNeedCheck; //成员是否要审核，1：是；0：否；开启时，所加入的成员需审核后才能加入群组。
    private Integer memberNum; //成员人数

    /**
     * 默认空构造函数
     */
    public BasGroup() {
        super();
    }

    /**
     * @return groupCode 群号,9位数字构成,类似于QQ群的群号
     */
    public String getGroupCode() {
        return this.groupCode;
    }

    /**
     * @param groupCode 群号,9位数字构成,类似于QQ群的群号
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * @return groupName 群组名称
     */
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * @param groupName 群组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return introduce 群组介绍
     */
    public String getIntroduce() {
        return this.introduce;
    }

    /**
     * @param introduce 群组介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * @return memberLimit 成员人数限制,-1表示无限制（该字段用于日后增值考虑）
     */
    public Integer getMemberLimit() {
        return this.memberLimit;
    }

    /**
     * @param memberLimit 成员人数限制,-1表示无限制（该字段用于日后增值考虑）
     */
    public void setMemberLimit(Integer memberLimit) {
        this.memberLimit = memberLimit;
    }

    /**
     * @return isNeedCheck 成员是否要审核，1：是；0：否；开启时，所加入的成员需审核后才能加入群组。
     */
    public Integer getIsNeedCheck() {
        return this.isNeedCheck;
    }

    /**
     * @param isNeedCheck 成员是否要审核，1：是；0：否；开启时，所加入的成员需审核后才能加入群组。
     */
    public void setIsNeedCheck(Integer isNeedCheck) {
        this.isNeedCheck = isNeedCheck;
    }

    /**
     * @return memberNum 成员人数
     */
    public Integer getMemberNum() {
        return this.memberNum;
    }

    /**
     * @param memberNum 成员人数
     */
    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupCode", getGroupCode())
                .append("groupName", getGroupName())
                .append("introduce", getIntroduce())
                .append("memberLimit", getMemberLimit())
                .append("isNeedCheck", getIsNeedCheck())
                .append("memberNum", getMemberNum())
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
                .append(getGroupCode())
                .append(getGroupName())
                .append(getIntroduce())
                .append(getMemberLimit())
                .append(getIsNeedCheck())
                .append(getMemberNum())
                .append(getCreator())
                .append(getCreateDate())
                .append(getLastModifier())
                .append(getLastModDate())
                .append(getStatus())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof BasGroup == false) return false;
        if (this == obj) return true;
        BasGroup other = (BasGroup) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}
