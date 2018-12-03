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

package cn.thinkjoy.qky.classgroup.dao.user;

import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 《群组与用户关系表，一个用户可以有N个群组》 数据访问接口
 *
 * @author linyuewei
 */
public interface IUserRelDAO extends IQkyBaseDAO<UserRel> {
    List<BasUserEx> fetchGroupRelUser(@Param("groupId") Long groupId);
}
