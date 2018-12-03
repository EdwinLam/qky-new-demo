/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：消息总表
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

package cn.thinkjoy.qky.classgroup.service.biz;

import cn.thinkjoy.qky.classgroup.common.SourceCodeEnum;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.biz.IBizMessageDAO;
import cn.thinkjoy.qky.classgroup.domain.QkyCreateBaseDomain;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.biz.BizEnroll;
import cn.thinkjoy.qky.classgroup.domain.biz.BizMessage;
import cn.thinkjoy.qky.classgroup.domain.biz.BizNotice;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《消息总表》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("BizMessageServiceImpl")
public class BizMessageServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<BizMessage>, BizMessage> implements IBizMessageService<IQkyBaseDAO<BizMessage>, BizMessage> {
    @Autowired
    private IBizMessageDAO bizMessageDAO;

    @Override
    public IQkyBaseDAO<BizMessage> getDao() {
        return bizMessageDAO;
    }

    /**
     * 添加信息
     *
     * @param curUser
     * @param item
     * @return
     */
    @Override
    @Transactional
    public BizMessage addMessage(BasUser curUser, QkyCreateBaseDomain item) {
        BizMessage bizMessage = new BizMessage();
        ResultUtils.initCreateDomain(bizMessage, curUser);
        bizMessage.setCreatorName(curUser.getNickName());
        if (item instanceof BizNotice) {
            BizNotice bizNotice = (BizNotice) item;
            bizMessage.setMessageTitle(bizNotice.getTitle());
            bizMessage.setMessageContent(bizNotice.getContent());
            bizMessage.setGroupId(bizNotice.getId());
            bizMessage.setSourceCode(SourceCodeEnum.NOTICE.getValue());
            bizMessageDAO.insert(bizMessage);
        } else if (item instanceof BizEnroll) {
            BizEnroll bizEnroll = (BizEnroll) item;
            bizMessage.setMessageTitle(bizEnroll.getTitle());
            bizMessage.setMessageContent(bizEnroll.getContent());
            bizMessage.setSourceCode(SourceCodeEnum.ENROLL.getValue());
            bizMessage.setGroupId(bizEnroll.getGroupId());
            bizMessageDAO.insert(bizMessage);
        }
        return bizMessage;
    }

}
