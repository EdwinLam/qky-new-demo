package cn.thinkjoy.qky.classgroup.service;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.impl.AbstractPageService;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tengen on 2016/1/14.
 * 分页处理的抽象实现，具备业务模型的基本业务逻辑处理
 */
public abstract class AbstractQkyPageService<D extends IQkyBaseDAO, T extends BaseDomain> extends AbstractPageService<D, T> implements IBatch<T> {
    @Override
    public int insertBatch(List<T> insertList) {
        if (insertList != null && insertList.size() > 0) {
            return getDao().insertBatch(insertList);
        }
        return 0;
    }

    @Override
    public int updateBatch(List<T> updateList) {
        if (updateList != null && updateList.size() > 0) {
            return getDao().updateBatch(updateList);
        }
        return 0;
    }

    @Override
    public int deleteBatch(List<T> deleteList) {
        if (deleteList != null && deleteList.size() > 0) {
            List<String> ids = new ArrayList<>();
            for (T t : deleteList) {
                ids.add(String.valueOf(t.getId()));
            }
            return getDao().deleteByIds(ids);
        }
        return 0;
    }
}
