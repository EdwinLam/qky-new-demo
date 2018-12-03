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
{  2018-11-27  linyuewei        新建
{
{  ---------------------------------------------------------------------------
{  注：本模块代码由codgen代码生成工具辅助生成 http://www.oschina.net/p/codgen
{*****************************************************************************
*/

package cn.thinkjoy.qky.classgroup.service.enroll;

import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.enroll.IEnrollUserDAO;
import cn.thinkjoy.qky.classgroup.domain.enroll.EnrollUser;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 《活动报名名单表，类似接龙游戏》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("EnrollUserServiceImpl")
public class EnrollUserServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<EnrollUser>, EnrollUser> implements IEnrollUserService<IQkyBaseDAO<EnrollUser>, EnrollUser> {
    @Autowired
    private IEnrollUserDAO enrollUserDAO;

    @Override
    public IQkyBaseDAO<EnrollUser> getDao() {
        return enrollUserDAO;
    }

}
