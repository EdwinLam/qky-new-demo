package cn.thinkjoy.qky.classgroup.controller.biz.notice;

import cn.hutool.core.bean.BeanUtil;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.qky.classgroup.common.DataStatusEnum;
import cn.thinkjoy.qky.classgroup.common.ReadStatusEnum;
import cn.thinkjoy.qky.classgroup.controller.BaseController;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import cn.thinkjoy.qky.classgroup.domain.biz.BizNotice;
import cn.thinkjoy.qky.classgroup.domain.biz.BizNoticeEx;
import cn.thinkjoy.qky.classgroup.domain.notice.NoticeReceive;
import cn.thinkjoy.qky.classgroup.param.biz.BizNoticeParamEx;
import cn.thinkjoy.qky.classgroup.param.notice.NoticeReceiveParam;
import cn.thinkjoy.qky.classgroup.service.biz.IBizNoticeService;
import cn.thinkjoy.qky.classgroup.service.notice.INoticeReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Edwin
 * @Date: 2018/11/28 11:31
 * @Description:
 */
@RestController
@RequestMapping({"/biz/notice"})
public class BizNoticeController extends BaseController {
    @Autowired
    IBizNoticeService bizNoticeService;
    @Autowired
    INoticeReceiveService noticeReceiveService;

    /**
     * 分页查询通知列表
     *
     * @param bizNoticeParam
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/page")
    public BizData4Page page(BizNoticeParamEx bizNoticeParam, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) throws InvocationTargetException, IllegalAccessException {
        BasUserEx curUser = getCurrUser();
        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setConditions(bizNoticeParam.toSearchFieldMap());
        bizData4Page.setPage(pageNo);
        bizData4Page.setPagesize(pageSize);
        bizNoticeService.queryPageByDataPerm(bizData4Page, BizNoticeParamEx.F_CreateDate, SqlOrderEnum.DESC);
        if (bizData4Page.getRows().size() > 0) {
            // 获取用户已读列表
            NoticeReceiveParam noticeReceiveParam = new NoticeReceiveParam();
            noticeReceiveParam.setStatus(DataStatusEnum.ENABLED.getValue());
            noticeReceiveParam.setUserId(curUser.getId());
            noticeReceiveParam.setGroupId(curUser.getCurBasGroup().getId());
            Map<Long, Long> readNoticeMap = new HashMap<>();
            List<NoticeReceive> noticeReceives = noticeReceiveService.queryList(noticeReceiveParam.toMap(), "id", "desc");
            for (NoticeReceive noticeReceive : noticeReceives) {
                if (noticeReceive.getReadStatus() == ReadStatusEnum.READ.getValue())
                    readNoticeMap.put(noticeReceive.getNoticeId(), noticeReceive.getId());
            }
            List<BizNoticeEx> finalBizNotices = new ArrayList<>();
            List<BizNotice> bizNotices = bizData4Page.getRows();
            for (BizNotice bizNotice : bizNotices) {
                BizNoticeEx finalBizNotice = new BizNoticeEx();
                BeanUtil.copyProperties(bizNotice,finalBizNotice);
                finalBizNotice.setRead(readNoticeMap.get(bizNotice.getId()) != null);
                finalBizNotices.add(finalBizNotice);
            }
            bizData4Page.setRows(finalBizNotices);
        }
        return bizData4Page;
    }

    /**
     * 添加通知
     *
     * @param bizNoticeParam
     * @return
     */
    @RequestMapping(value = "/add")
    public BizNoticeEx add(@RequestBody BizNoticeParamEx bizNoticeParam) {
        BizNoticeEx bizNotice = bizNoticeParam.getBizNotice();
        if (bizNoticeService.validateAddNotice(bizNotice)) {
            return bizNoticeService.addNotice(getCurrUser(), bizNotice);
        }
        return bizNotice;
    }

    /**
     * 获取通知详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}")
    public BizNoticeEx view(@PathVariable Long id) throws InvocationTargetException, IllegalAccessException {
        // 补充接收人列表
//        bizNoticeService.fillNoticeReceive(getCurrUser(),id);
        // 标记通知已读
        bizNoticeService.readNotice(getCurrUser(), id);
        return bizNoticeService.viewNotice(id);
    }

}
