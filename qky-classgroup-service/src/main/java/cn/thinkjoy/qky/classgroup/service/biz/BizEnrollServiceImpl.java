/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：报名主表
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

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.qky.classgroup.common.*;
import cn.thinkjoy.qky.classgroup.common.utils.ResultUtils;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;
import cn.thinkjoy.qky.classgroup.dao.biz.IBizEnrollDAO;
import cn.thinkjoy.qky.classgroup.domain.bas.BasAtts;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import cn.thinkjoy.qky.classgroup.domain.biz.BizEnroll;
import cn.thinkjoy.qky.classgroup.domain.biz.BizEnrollEx;
import cn.thinkjoy.qky.classgroup.domain.enroll.EnrollReceive;
import cn.thinkjoy.qky.classgroup.domain.enroll.EnrollUser;
import cn.thinkjoy.qky.classgroup.domain.user.UserRel;
import cn.thinkjoy.qky.classgroup.param.bas.BasAttsParam;
import cn.thinkjoy.qky.classgroup.param.enroll.EnrollReceiveParam;
import cn.thinkjoy.qky.classgroup.param.enroll.EnrollUserParam;
import cn.thinkjoy.qky.classgroup.param.user.UserRelParam;
import cn.thinkjoy.qky.classgroup.service.AbstractQkyPageService;
import cn.thinkjoy.qky.classgroup.service.bas.IBasAttsService;
import cn.thinkjoy.qky.classgroup.service.bas.IBasGroupService;
import cn.thinkjoy.qky.classgroup.service.enroll.IEnrollReceiveService;
import cn.thinkjoy.qky.classgroup.service.enroll.IEnrollUserService;
import cn.thinkjoy.qky.classgroup.service.user.IUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 《报名主》 业务逻辑服务类
 *
 * @author linyuewei
 */
@Service("BizEnrollServiceImpl")
public class BizEnrollServiceImpl extends AbstractQkyPageService<IQkyBaseDAO<BizEnroll>, BizEnroll> implements IBizEnrollService<IQkyBaseDAO<BizEnroll>, BizEnroll> {
    @Autowired
    private IBizEnrollDAO bizEnrollDAO;
    @Autowired
    private IBizMessageService bizMessageService;
    @Autowired
    private IUserRelService userRelService;
    @Autowired
    private IEnrollReceiveService enrollReceiveService;
    @Autowired
    private IBasGroupService basGroupService;
    @Autowired
    private IBasAttsService basAttsService;
    @Autowired
    private IEnrollUserService enrollUserService;

    @Override
    public IQkyBaseDAO<BizEnroll> getDao() {
        return bizEnrollDAO;
    }

    /**
     * 添加报名
     *
     * @param curUser
     * @param bizEnroll
     */
    @Override
    @Transactional
    public BizEnrollEx addEnroll(BasUser curUser, BizEnrollEx bizEnroll) {
        ResultUtils.initCreateDomain(bizEnroll, curUser);
        bizEnroll.setCreatorName(curUser.getNickName());
        bizEnroll.setReadNum(0);
        bizEnroll.setEnrollNum(0);
        if (bizEnrollDAO.insert(bizEnroll) > 0) {
            // 记录到信息
            bizMessageService.addMessage(curUser, bizEnroll);
            // 记录接收人
            initEnrollReceive(curUser, bizEnroll.getGroupId(), bizEnroll.getId(), null);
            // 记录通知附件
            if (bizEnroll.getBasAtts().size() > 0)
                basAttsService.batchAddAttr(SourceCodeEnum.ENROLL.getValue(), bizEnroll.getId(), curUser, bizEnroll.getBasAtts());
        }
        return bizEnroll;
    }

    /**
     * 报名
     *
     * @param curUser
     * @param enrollId
     * @param remark
     */
    public void enroll(BasUser curUser, Long enrollId, String remark) {
        // 获取报名详情
        // 查询是否已经存在报名
        BizEnroll bizEnroll = bizEnrollDAO.fetch(enrollId);
        EnrollUserParam param = new EnrollUserParam();
        param.setUserId(curUser.getId());
        param.setEnrollId(enrollId);
        EnrollUser enrollUser = (EnrollUser) enrollUserService.queryOne(param.toMap());
        if (enrollUser != null && enrollUser.getStatus() == DataStatusEnum.DISABLED.getValue()) {
            enrollUser.setStatus(DataStatusEnum.ENABLED.getValue());
            enrollUser.setNickName(curUser.getNickName());
            enrollUserService.update(enrollUser);
        } else {
            enrollUser = new EnrollUser();
            ResultUtils.initCreateDomain(enrollUser, curUser);
            enrollUser.setEnrollId(enrollId);
            enrollUser.setNickName(curUser.getNickName());
            enrollUser.setRemark(remark);
            enrollUser.setEnrollId(enrollId);
            enrollUser.setGroupId(bizEnroll.getGroupId());
            enrollUserService.insert(enrollUser);
        }
        // 更新报名人数
        param = new EnrollUserParam();
        param.setEnrollId(enrollId);
        param.setStatus(DataStatusEnum.ENABLED.getValue());
        bizEnroll.setEnrollNum(enrollUserService.count(param.toMap()));
        bizEnrollDAO.update(bizEnroll);
    }

    /**
     * 标记报名已读
     *
     * @param curUser
     * @param id
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    @Transactional
    public void readEnroll(BasUser curUser, long id) throws InvocationTargetException, IllegalAccessException {
        BizEnroll bizEnroll = bizEnrollDAO.fetch(id);
        if (bizEnroll == null) {
            return;
        }
        EnrollReceiveParam param = new EnrollReceiveParam();
        param.setEnrollId(bizEnroll.getId());
        param.setUserId(curUser.getId());
        EnrollReceive enrollReceive = (EnrollReceive) enrollReceiveService.queryOne(param.toMap());
        if (enrollReceive != null) {
            enrollReceive.setReadStatus(ReadStatusEnum.READ.getValue());
            ResultUtils.initUpdateDomain(enrollReceive, curUser);
            enrollReceiveService.update(enrollReceive);
        } else {
            // 获取用户所在分组信息
            UserRelParam userRelParam = new UserRelParam();
            userRelParam.setUserId(curUser.getId());
            userRelParam.setGroupId(bizEnroll.getGroupId());
            UserRel userRel = (UserRel) userRelService.queryOne(userRelParam.toMap());
            if (userRel != null && userRel.getStatus() != DataStatusEnum.DISABLED.getValue()
                    && userRel.getUserStatus() == GroupUserStatusEnum.FULL_MEMBER.getValue()) {
                BasUserEx basUser = new BasUserEx();
                BeanUtil.copyProperties(curUser,basUser);
                basUser.setGroupNickName(userRel.getNickName());
                enrollReceive = initEnrollReceiveDomain(curUser, basUser, bizEnroll.getGroupId(), id);
                enrollReceive.setReadStatus(ReadStatusEnum.READ.getValue());
                enrollReceiveService.insert(enrollReceive);
            }
        }
        // 更新已读数
        bizEnroll.setReadNum(enrollReceiveService.getReadNum(id));
        bizEnrollDAO.update(bizEnroll);
    }

    /**
     * 根据id查看报名
     *
     * @param id
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public BizEnrollEx viewEnroll(long id) throws InvocationTargetException, IllegalAccessException {
        BizEnrollEx bizEnrollEx = new BizEnrollEx();
        BizEnroll bizEnroll = bizEnrollDAO.fetch(id);
        if (bizEnroll == null) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "找不到对应的报名");
        }
        BeanUtil.copyProperties(bizEnroll,bizEnrollEx);
        // 获取图片列表
        BasAttsParam basAttsParam = new BasAttsParam();
        basAttsParam.setSourceCode(SourceCodeEnum.NOTICE.getValue());
        basAttsParam.setSourceId(bizEnrollEx.getId());
        basAttsParam.setStatus(DataStatusEnum.ENABLED.getValue());
        List<BasAtts> baseAtts = basAttsService.queryList(basAttsParam.toMap(), "id", "desc");
        bizEnrollEx.setBasAtts(baseAtts);
        // 获取报名列表
        EnrollUserParam enrollUserParam = new EnrollUserParam();
        enrollUserParam.setStatus(DataStatusEnum.ENABLED.getValue());
        enrollUserParam.setEnrollId(id);
        List<EnrollUser> enrollUsers = enrollReceiveService.queryList(enrollUserParam.toMap(), EnrollUserParam.F_CreateDate, SqlOrderEnum.DESC.getAction());
        bizEnrollEx.setEnrollUsers(enrollUsers);
        return bizEnrollEx;
    }

    /**
     * 获取报名状态
     *
     * @param userId
     * @param bizEnroll
     * @return
     */
    @Override
    public int getEnrollStatus(Long userId, BizEnroll bizEnroll) {
        boolean isOverTime = bizEnroll.getEndDate() < new Date().getTime();
        // 判断是否超过人数
        boolean isOverNum = bizEnroll.getEnrollNum() > bizEnroll.getLimitNum() && bizEnroll.getLimitNum() != MemberLimitEnum.NO_LIMIT.getValue();
        if (isOverTime) {
            return EnrollStatusEnum.END_ENROLL.getValue();
        } else if (isOverNum) {
            return EnrollStatusEnum.FUll_ENROLL.getValue();
        } else {
            // 判断是否报名
            EnrollUserParam enrollUserParam = new EnrollUserParam();
            enrollUserParam.setStatus(DataStatusEnum.ENABLED.getValue());
            enrollUserParam.setEnrollId(bizEnroll.getId());
            enrollUserParam.setUserId(userId);
            if (enrollUserService.count(enrollUserParam.toMap()) > 0) {
                return EnrollStatusEnum.FINISH_ENROLL.getValue();
            } else {
                return EnrollStatusEnum.NO_ENROLL.getValue();
            }
        }
    }

    /**
     * 报名验证
     *
     * @param basUser
     * @param enrollId
     * @return
     */
    @Override
    public boolean validateDoEnroll(BasUser basUser, Long enrollId) {
        // 获取报名信息
        BizEnroll bizEnroll = bizEnrollDAO.fetch(enrollId);
        if (bizEnroll == null)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "不存在报名项目");
        // 获取报名状态
        int enrollStatus = getEnrollStatus(basUser.getId(), bizEnroll);
        if (enrollStatus == EnrollStatusEnum.END_ENROLL.getValue()) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "报名已截止");
        }
        if (enrollStatus == EnrollStatusEnum.FUll_ENROLL.getValue()) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "报名名额已满");
        }
        if (enrollStatus == EnrollStatusEnum.FINISH_ENROLL.getValue()) {
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "已报名不能重复报名");
        }
        return true;
    }


    /**
     * 添加报名项目验证
     *
     * @param bizEnroll
     * @return
     */
    @Override
    public boolean validateAddEnroll(BizEnrollEx bizEnroll) {
        if (basGroupService.isExistGroup(bizEnroll.getGroupId()))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "不存在加入的分组");
        if (StrUtil.isBlank(bizEnroll.getTitle()))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知标题不能为空");
        if (StrUtil.isBlank(bizEnroll.getContent()))
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知内容不能为空");
        if (bizEnroll.getTitle().length() > 20)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知标题不能多于20字");
        if (bizEnroll.getContent().length() > 300)
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR.getCode(), "通知内容不能多于300字");
        return true;
    }


    /**
     * 初始化接收人记录
     *
     * @param curUser
     * @param groupId
     * @param excludeIds 不处理用户id列表
     */
    public void initEnrollReceive(BasUser curUser, Long groupId, Long noticeId, List<Long> excludeIds) {
        List<EnrollReceive> enrollReceives = new ArrayList<>();
        List<BasUserEx> basUsers = userRelService.fetchGroupRelUser(groupId);
        for (BasUserEx basUser : basUsers) {
            if (excludeIds == null || excludeIds.contains(basUser.getId())) {
                enrollReceives.add(initEnrollReceiveDomain(curUser, basUser, groupId, noticeId));
            }
        }
        if (enrollReceives.size() > 0) {
            enrollReceiveService.insertBatch(enrollReceives);
        }
    }


    /**
     * 初始化报名阅读者实体
     *
     * @param basUser
     * @param enrollId
     * @return
     */
    private EnrollReceive initEnrollReceiveDomain(BasUser curUser, BasUserEx basUser, Long groupId, Long enrollId) {
        EnrollReceive enrollReceive = new EnrollReceive();
        ResultUtils.initCreateDomain(enrollReceive, curUser);
        enrollReceive.setNickName(basUser.getGroupNickName());
        enrollReceive.setReadStatus(ReadStatusEnum.UNREAD.getValue());
        enrollReceive.setUserId(basUser.getId());
        enrollReceive.setEnrollId(enrollId);
        enrollReceive.setGroupId(groupId);
        return enrollReceive;
    }


}
