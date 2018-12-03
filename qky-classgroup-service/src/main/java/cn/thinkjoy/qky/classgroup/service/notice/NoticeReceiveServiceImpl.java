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
{  2018-11-27  linyuewei        新建
{
{  ---------------------------------------------------------------------------
{  注：本模块代码由codgen代码生成工具辅助生成 http://www.oschina.net/p/codgen
{*****************************************************************************
*/

package cn.thinkjoy.qky.classgroup.service.notice;

import cn.thinkjoy.qky.classgroup.common.DataStatusEnum;
import cn.thinkjoy.qky.classgroup.common.ReadStatusEnum;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.notice.INoticeReceiveDAO;
import cn.thinkjoy.qky.classgroup.domain.notice.NoticeReceive;
import cn.thinkjoy.qky.classgroup.param.notice.NoticeReceiveParam;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 《通知接收情况》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("NoticeReceiveServiceImpl")
public class NoticeReceiveServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<NoticeReceive>, NoticeReceive> implements INoticeReceiveService<IQkyBaseDAO<NoticeReceive>, NoticeReceive> {
    @Autowired
    private INoticeReceiveDAO noticeReceiveDAO;

    @Override
    public IQkyBaseDAO<NoticeReceive> getDao() {
        return noticeReceiveDAO;
    }

    /**
     * 获取通知已读数量
     *
     * @param noticeId
     * @return
     */
    @Override
    public int getReadNum(Long noticeId) {
        NoticeReceiveParam noticeReceiveParam = new NoticeReceiveParam();
        noticeReceiveParam.setStatus(DataStatusEnum.ENABLED.getValue());
        noticeReceiveParam.setReadStatus(ReadStatusEnum.READ.getValue());
        noticeReceiveParam.setNoticeId(noticeId);
        return noticeReceiveDAO.count(noticeReceiveParam.toMap());
    }

}
