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

import cn.thinkjoy.qky.classgroup.common.GroupUserIdentityEnum;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;

/**
 * 《用户基本信息》扩展实体
 *
 * @author linyuewei
 */
public class BasUserEx extends BasUser {
    private static final long serialVersionUID = 1L;

    //针对BasUser实体在这里增加额外的属性和对应的get和set方法
    private String username;
    private String password;
    private String session_key;
    /**
     * 阅读状态（0：未读；1：已读)
     */
    private Integer readStatus;
    /**
     * 用户在群的名称
     */
    private String groupNickName;
    /**
     * 用户身份
     */
    private int userIdentity;
    /**
     * 用户所在群组
     */
    private BasGroupEx curBasGroup;

    private UserRel userRel;

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getGroupNickName() {
        return groupNickName;
    }

    public void setGroupNickName(String groupNickName) {
        this.groupNickName = groupNickName;
    }


    public BasGroupEx getCurBasGroup() {
        return curBasGroup;
    }

    public void setCurBasGroup(BasGroupEx curBasGroup) {
        this.curBasGroup = curBasGroup;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
    }

    public boolean isAdmin() {
        return this.userIdentity == GroupUserIdentityEnum.ADMIN.getValue();
    }

    public boolean isGroupOwner() {
        return this.userIdentity == GroupUserIdentityEnum.GROUP_OWNER.getValue();
    }

    public UserRel getUserRel() {
        return userRel;
    }

    public void setUserRel(UserRel userRel) {
        this.userRel = userRel;
    }

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
