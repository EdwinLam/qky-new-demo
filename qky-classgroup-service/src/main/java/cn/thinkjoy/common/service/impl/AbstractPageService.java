//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.utils.BizData4PageBuilder;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import java.util.List;
import java.util.Map;

public abstract class AbstractPageService<D extends IBaseDAO, T extends BaseDomain> extends AbstractBaseService<D, T> implements IPageService<D, T> {
    public AbstractPageService() {
    }

    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows) {
        return BizData4PageBuilder.createBizData4Page(this.getDao(), conditions, curPage, offset, rows);
    }

    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return BizData4PageBuilder.createBizData4Page(this.getDao(), conditions, curPage, offset, rows);
    }

    public void queryPageByDataPerm(BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum) {
        int offset = (bizData4Page.getPage() - 1) * bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = this.getDao().queryPage(bizData4Page.getConditions(), offset, rows, orderBy, sqlOrderEnum.getAction(), (Map)null);
        int records = this.getDao().count(bizData4Page.getConditions());
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);
        int total = records / rows;
        int mod = records % rows;
        if (mod > 0) {
            ++total;
        }

        bizData4Page.setTotal(total);
    }

    public void queryPageByDataPerm(BizData4Page bizData4Page) {
        int offset = (bizData4Page.getPage() - 1) * bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = this.getDao().queryPage(bizData4Page.getConditions(), offset, rows, (String)null, (String)null, (Map)null);
        int records = this.getDao().count(bizData4Page.getConditions());
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);
        int total = records / rows;
        int mod = records % rows;
        if (mod > 0) {
            ++total;
        }

        bizData4Page.setTotal(total);
    }

    public void queryPageByDataPerm(IBaseDAO baseDAO, BizData4Page bizData4Page) {
        int offset = (bizData4Page.getPage() - 1) * bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = baseDAO.queryPage(bizData4Page.getConditions(), offset, rows, (String)null, (String)null, (Map)null);
        int records = baseDAO.count(bizData4Page.getConditions());
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);
        int total = records / rows;
        int mod = records % rows;
        if (mod > 0) {
            ++total;
        }

        bizData4Page.setTotal(total);
    }

    public void queryPageByDataPerm(IBaseDAO baseDAO, BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum) {
        int offset = (bizData4Page.getPage() - 1) * bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = baseDAO.queryPage(bizData4Page.getConditions(), offset, rows, orderBy, sqlOrderEnum.getAction(), (Map)null);
        int records = baseDAO.count(bizData4Page.getConditions());
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);
        int total = records / rows;
        int mod = records % rows;
        if (mod > 0) {
            ++total;
        }

        bizData4Page.setTotal(total);
    }

    public void queryPageByDataPerm(BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector) {
        this.queryPageByDataPerm(this.getDao(), bizData4Page, orderBy, sqlOrderEnum, selector);
    }

    public void queryPageByDataPerm(IBaseDAO dao, BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector) {
        int offset = (bizData4Page.getPage() - 1) * bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = dao.queryPage(bizData4Page.getConditions(), offset, rows, orderBy, sqlOrderEnum.getAction(), selector);
        int records = dao.count(bizData4Page.getConditions());
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);
        int total = (records - 1) / rows + 1;
        bizData4Page.setTotal(total);
    }

    public BizData4Page queryPageByDataPerm(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector) {
        List mainData = dao.queryPage(conditions, offset, rows, orderBy, sqlOrderEnum.getAction(), selector);
        int records = dao.count(conditions);
        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(curPage);
        bizData4Page.setRecords(records);
        int total = records / rows;
        int mod = records % rows;
        if (mod > 0) {
            ++total;
        }

        bizData4Page.setTotal(total);
        return bizData4Page;
    }
}
