/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：通知表
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

package cn.thinkjoy.qky.classgroup.service.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.qky.classgroup.common.*;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.biz.IBizNoticeDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasAtts;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import cn.thinkjoy.qky.classgroup.domain.biz.BizNotice;
import cn.thinkjoy.qky.classgroup.domain.biz.BizNoticeEx;
import cn.thinkjoy.qky.classgroup.domain.notice.NoticeReceive;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import cn.thinkjoy.qky.classgroup.param.bas.BasAttsParam;
import cn.thinkjoy.qky.classgroup.param.notice.NoticeReceiveParam;
import cn.thinkjoy.qky.classgroup.param.user.UserRelParam;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import cn.thinkjoy.qky.classgroup.service.bas.IBasAttsService;
import cn.thinkjoy.qky.classgroup.service.bas.IBasGroupService;
import cn.thinkjoy.qky.classgroup.service.notice.INoticeReceiveService;
import cn.thinkjoy.qky.classgroup.service.user.IUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 《通知》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("BizNoticeServiceImpl")
public class BizNoticeServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<BizNotice>, BizNotice> implements IBizNoticeService<IQkyBaseDAO<BizNotice>, BizNotice> {
    @Autowired
    private IBizNoticeDAO bizNoticeDAO;
    @Autowired
    private IBasGroupService basGroupService;
    @Autowired
    private IBasAttsService basAttsService;
    @Autowired
    private IUserRelService userRelService;
    @Autowired
    private INoticeReceiveService noticeReceiveService;
    @Autowired
    private IBizMessageService bizMessageService;

    @Override
    public IQkyBaseDAO<BizNotice> getDao() {
        return bizNoticeDAO;
    }

    /**
     * 添加通知
     *
     * @param curUser
     * @param bizNotice
     */
    @Override
    @Transactional
    public BizNoticeEx addNotice(BasUser curUser, BizNoticeEx bizNotice) {
        ResultUtils.initCreateDomain(bizNotice, curUser);
        bizNotice.setCreatorName(curUser.getNickName());
        bizNotice.setReadNum(0);
        if (bizNoticeDAO.insert(bizNotice) > 0) {
            // 记录到信息
            bizMessageService.addMessage(curUser, bizNotice);
            // 记录接收人
            initNoticeReceive(curUser, bizNotice.getGroupId(), bizNotice.getId(), null);
            // 记录通知附件
            if (bizNotice.getBasAtts().size() > 0)
                basAttsService.batchAddAttr(SourceCodeEnum.NOTICE.getValue(), bizNotice.getId(), curUser, bizNotice.getBasAtts());
        }
        return bizNotice;
    }

    /**
     * 标记通知已读
     *
     * @param curUser
     * @param id
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    @Transactional
    public void readNotice(BasUser curUser, long id) throws InvocationTargetException, IllegalAccessException {
        BizNotice bizNotice = bizNoticeDAO.fetch(id);
        if (bizNotice == null) {
            return;
        }
        NoticeReceiveParam param = new NoticeReceiveParam();
        param.setNoticeId(bizNotice.getId());
        param.setUserId(curUser.getId());
        NoticeReceive noticeReceive = (NoticeReceive) noticeReceiveService.queryOne(param.toMap());
        if (noticeReceive != null) {
            noticeReceive.setReadStatus(ReadStatusEnum.READ.getValue());
            ResultUtils.initUpdateDomain(noticeReceive, curUser);
            noticeReceiveService.update(noticeReceive);
        } else {
            // 获取用户所在分组信息
            UserRelParam userRelParam = new UserRelParam();
            userRelParam.setUserId(curUser.getId());
            userRelParam.setGroupId(bizNotice.getGroupId());
            UserRel userRel = (UserRel) userRelService.queryOne(userRelParam.toMap());
            if (userRel != null && userRel.getStatus() != DataStatusEnum.DISABLED.getValue()
                    && userRel.getUserStatus() == GroupUserStatusEnum.FULL_MEMBER.getValue()) {
                BasUserEx basUser = new BasUserEx();
                BeanUtil.copyProperties(curUser,basUser);
                basUser.setGroupNickName(userRel.getNickName());
                noticeReceive = initNoticeReceiveDomain(curUser, basUser, bizNotice.getGroupId(), id);
                noticeReceive.setReadStatus(ReadStatusEnum.READ.getValue());
                noticeReceiveService.insert(noticeReceive);
            }
        }
        // 更新已读数
        bizNotice.setReadNum(noticeReceiveService.getReadNum(id));
        bizNoticeDAO.update(bizNotice);

    }


    /**
     * 根据id查看通知详情
     *
     * @param id
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public BizNoticeEx viewNotice(long id) throws InvocationTargetException, IllegalAccessException {
        BizNoticeEx bizNoticeEx = new BizNoticeEx();
        BizNotice bizNotice = bizNoticeDAO.fetch(id);
        if (bizNotice == null) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "找不到对应的通知");
        }
        BeanUtil.copyProperties(bizNotice,bizNoticeEx);
        // 获取图片列表
        BasAttsParam basAttsParam = new BasAttsParam();
        basAttsParam.setSourceCode(SourceCodeEnum.NOTICE.getValue());
        basAttsParam.setSourceId(bizNoticeEx.getId());
        basAttsParam.setStatus(DataStatusEnum.ENABLED.getValue());
        List<BasAtts> baseAtts = basAttsService.queryList(basAttsParam.toMap(), "id", "desc");
        bizNoticeEx.setBasAtts(baseAtts);
        // 获取未读以及已读列表
        List<BasUserEx> basUsers = fetchNoticeRelUser(bizNoticeEx.getId());
        List<BasUser> readUsers = new ArrayList<>();//已读列表
        List<BasUser> unReadUsers = new ArrayList<>();//未读列表
        for (BasUserEx basUser : basUsers) {
            if (basUser.getReadStatus() == ReadStatusEnum.READ.getValue()) {
                readUsers.add(basUser);
            } else if (basUser.getReadStatus() == ReadStatusEnum.UNREAD.getValue()) {
                unReadUsers.add(basUser);
            }
        }
        bizNoticeEx.setReadUsers(readUsers);
        bizNoticeEx.setUnReadUsers(unReadUsers);
        return bizNoticeEx;
    }


    /**
     * 初始化接收人记录
     *
     * @param curUser
     * @param groupId
     * @param excludeIds 不处理用户id列表
     */
    public void initNoticeReceive(BasUser curUser, Long groupId, Long noticeId, List<Long> excludeIds) {
        List<NoticeReceive> noticeReceives = new ArrayList<>();
        List<BasUserEx> basUsers = userRelService.fetchGroupRelUser(groupId);
        for (BasUserEx basUser : basUsers) {
            if (excludeIds == null || excludeIds.contains(basUser.getId())) {
                noticeReceives.add(initNoticeReceiveDomain(curUser, basUser, groupId, noticeId));
            }
        }
        if (noticeReceives.size() > 0) {
            noticeReceiveService.insertBatch(noticeReceives);
        }
    }


    /**
     * 初始化接收人实体
     *
     * @param basUser
     * @param noticeId
     * @return
     */
    private NoticeReceive initNoticeReceiveDomain(BasUser curUser, BasUserEx basUser, Long groupId, Long noticeId) {
        NoticeReceive noticeReceive = new NoticeReceive();
        ResultUtils.initCreateDomain(noticeReceive, curUser);
        noticeReceive.setNickName(basUser.getGroupNickName());
        noticeReceive.setReadStatus(ReadStatusEnum.UNREAD.getValue());
        noticeReceive.setUserId(basUser.getId());
        noticeReceive.setNoticeId(noticeId);
        noticeReceive.setGroupId(groupId);
        return noticeReceive;
    }

    /**
     * 补充未读人
     *
     * @param curUser
     * @param groupId
     */
    @Override
    @Transactional
    public void fillNoticeReceive(BasUser curUser, Long groupId, Long noticeId) {
        NoticeReceiveParam noticeReceiveParam = new NoticeReceiveParam();
        noticeReceiveParam.setStatus(DataStatusEnum.ENABLED.getValue());
        noticeReceiveParam.setNoticeId(groupId);
        List<NoticeReceive> noticeReceives = new ArrayList<>();
        List<Long> receiveUserIds = new ArrayList<>();
        for (NoticeReceive noticeReceive : noticeReceives) {
            receiveUserIds.add(noticeReceive.getUserId());
        }
        initNoticeReceive(curUser, groupId, noticeId, receiveUserIds);
    }


    /**
     * 获取与通知有关的用户
     *
     * @param noticeId
     * @return
     */
    public List<BasUserEx> fetchNoticeRelUser(Long noticeId) {
        return bizNoticeDAO.fetchNoticeRelUser(noticeId);
    }


    /**
     * 增加通知验证
     *
     * @param bizNotice
     * @return
     */
    @Override
    public boolean validateAddNotice(BizNoticeEx bizNotice) {
        if (basGroupService.isExistGroup(bizNotice.getGroupId()))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "不存在加入的分组");
        if (StrUtil.isBlank(bizNotice.getTitle()))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知标题不能为空");
        if (StrUtil.isBlank(bizNotice.getContent()))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知内容不能为空");
        if (bizNotice.getTitle().length() > 20)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知标题不能多于20字");
        if (bizNotice.getContent().length() > 300)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知内容不能多于300字");
        if (bizNotice.getBasAtts().size() > 9)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "最多可以添加9张图片");
        return true;
    }


}
