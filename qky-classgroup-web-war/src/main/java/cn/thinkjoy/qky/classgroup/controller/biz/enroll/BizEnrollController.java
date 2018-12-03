package cn.thinkjoy.qky.classgroup.controller.biz.enroll;

import cn.hutool.core.bean.BeanUtil;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.qky.classgroup.controller.BaseController;
import cn.thinkjoy.qky.classgroup.domain.biz.BizEnroll;
import cn.thinkjoy.qky.classgroup.domain.biz.BizEnrollEx;
import cn.thinkjoy.qky.classgroup.param.biz.BizEnrollParamEx;
import cn.thinkjoy.qky.classgroup.param.biz.BizNoticeParamEx;
import cn.thinkjoy.qky.classgroup.service.biz.IBizEnrollService;
import cn.thinkjoy.qky.classgroup.service.enroll.IEnrollReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Edwin
 * @Date: 2018/11/28 11:31
 * @Description:
 */
@RestController
@RequestMapping({"/biz/enroll"})
public class BizEnrollController extends BaseController {
    @Autowired
    IBizEnrollService bizEnrollService;
    @Autowired
    IEnrollReceiveService enrollReceiveService;

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
        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setConditions(bizNoticeParam.toSearchFieldMap());
        bizData4Page.setPage(pageNo);
        bizData4Page.setPagesize(pageSize);
        bizEnrollService.queryPageByDataPerm(bizData4Page, BizNoticeParamEx.F_CreateDate, SqlOrderEnum.DESC);
        if (bizData4Page.getRows().size() > 0) {
            List<BizEnrollEx> finalBizEnrolls = new ArrayList<>();
            List<BizEnroll> bizEnrolls = bizData4Page.getRows();
            for (BizEnroll bizEnroll : bizEnrolls) {
                BizEnrollEx finalBizEnroll = new BizEnrollEx();
                BeanUtil.copyProperties(bizEnroll,finalBizEnroll);
                finalBizEnroll.setEnrollStatus(bizEnrollService.getEnrollStatus(getCurrUserId(), finalBizEnroll));
                finalBizEnrolls.add(finalBizEnroll);
            }
            bizData4Page.setRows(finalBizEnrolls);
        }
        return bizData4Page;
    }

    /**
     * 添加报名
     *
     * @param bizEnrollParam
     * @return
     */
    @RequestMapping(value = "/add")
    public BizEnrollEx add(@RequestBody BizEnrollParamEx bizEnrollParam) {
        BizEnrollEx bizEnroll = bizEnrollParam.getBizEnroll();
        if (bizEnrollService.validateAddEnroll(bizEnroll)) {
            return bizEnrollService.addEnroll(getCurrUser(), bizEnroll);
        }
        return bizEnroll;
    }

    /**
     * 获取详情报名
     *
     * @param id 报名项目id
     * @return
     */
    @RequestMapping(value = "/{id}")
    public BizEnrollEx view(@PathVariable Long id) throws InvocationTargetException, IllegalAccessException {
        // 标记报名已读
        bizEnrollService.readEnroll(getCurrUser(), id);
        BizEnrollEx bizEnroll = bizEnrollService.viewEnroll(id);
        // 获取报名状态
        bizEnroll.setEnrollStatus(bizEnrollService.getEnrollStatus(getCurrUserId(), bizEnroll));
        return bizEnroll;
    }


    /**
     * 报名
     *
     * @param id     报名项目id
     * @param remark 报名备注
     * @return
     */
    @RequestMapping(value = "/do/{id}")
    public boolean enroll(@PathVariable Long id, String remark) {
        if (bizEnrollService.validateDoEnroll(getCurrUser(), id)) {
            bizEnrollService.enroll(getCurrUser(), id, remark);
        }
        return true;
    }

}
