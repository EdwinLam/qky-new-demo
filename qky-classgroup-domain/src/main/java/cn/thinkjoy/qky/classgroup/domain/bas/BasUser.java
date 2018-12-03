/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：用户基本信息
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

package cn.thinkjoy.qky.classgroup.domain.bas;

import cn.thinkjoy.qky.classgroup.domain.QkyCreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《用户基本信息》 实体
 *
 * @author linyuewei
 */
public class BasUser extends QkyCreateBaseDomain<Long> {
    private static final long serialVersionUID = 1L;

    private String openid; //用户唯一标识
    private String unionid; //开放平台的唯一标识符
    private String nickName; //用户昵称
    private String avatarUrl; //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
    private Integer gender; //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String city; //用户所在城市
    private String province; //用户所在省份
    private String country; //
    private String phoneNumber; //用户绑定的手机号（国外手机号会有区号）
    private String purePhoneNumber; //没有区号的手机号
    private String countryCode; //

    /**
     * 默认空构造函数
     */
    public BasUser() {
        super();
    }

    /**
     * @return openid 用户唯一标识
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     * @param openid 用户唯一标识
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return unionid 开放平台的唯一标识符
     */
    public String getUnionid() {
        return this.unionid;
    }

    /**
     * @param unionid 开放平台的唯一标识符
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
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
     * @return avatarUrl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    /**
     * @param avatarUrl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return gender 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public Integer getGender() {
        return this.gender;
    }

    /**
     * @param gender 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return city 用户所在城市
     */
    public String getCity() {
        return this.city;
    }

    /**
     * @param city 用户所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return province 用户所在省份
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * @param province 用户所在省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return phoneNumber 用户绑定的手机号（国外手机号会有区号）
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @param phoneNumber 用户绑定的手机号（国外手机号会有区号）
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return purePhoneNumber 没有区号的手机号
     */
    public String getPurePhoneNumber() {
        return this.purePhoneNumber;
    }

    /**
     * @param purePhoneNumber 没有区号的手机号
     */
    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    /**
     * @return countryCode
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("openid", getOpenid())
                .append("unionid", getUnionid())
                .append("nickName", getNickName())
                .append("avatarUrl", getAvatarUrl())
                .append("gender", getGender())
                .append("city", getCity())
                .append("province", getProvince())
                .append("country", getCountry())
                .append("phoneNumber", getPhoneNumber())
                .append("purePhoneNumber", getPurePhoneNumber())
                .append("countryCode", getCountryCode())
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
                .append(getOpenid())
                .append(getUnionid())
                .append(getNickName())
                .append(getAvatarUrl())
                .append(getGender())
                .append(getCity())
                .append(getProvince())
                .append(getCountry())
                .append(getPhoneNumber())
                .append(getPurePhoneNumber())
                .append(getCountryCode())
                .append(getCreator())
                .append(getCreateDate())
                .append(getLastModifier())
                .append(getLastModDate())
                .append(getStatus())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof BasUser == false) return false;
        if (this == obj) return true;
        BasUser other = (BasUser) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}
