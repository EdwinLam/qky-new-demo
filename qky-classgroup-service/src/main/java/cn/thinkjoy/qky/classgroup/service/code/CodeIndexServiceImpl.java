/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：groupCode值记录表
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

package cn.thinkjoy.qky.classgroup.service.code;

import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.code.ICodeIndexDAO;
import cn.thinkjoy.qky.classgroup.domain.code.CodeIndex;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 《groupCode值记录》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("CodeIndexServiceImpl")
public class CodeIndexServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<CodeIndex>, CodeIndex> implements ICodeIndexService<IQkyBaseDAO<CodeIndex>, CodeIndex> {
    @Autowired
    private ICodeIndexDAO codeIndexDAO;

    @Override
    public IQkyBaseDAO<CodeIndex> getDao() {
        return codeIndexDAO;
    }

}
