package cn.thinkjoy.qky.classgroup.service;

import cn.thinkjoy.common.domain.BaseDomain;

import java.util.List;

/**
 * Created by tengen on 2018/5/11.
 */
public interface IBatch<T extends BaseDomain> {
    /**
     * 批量插入
     *
     * @param insertList
     * @return
     */
    int insertBatch(List<T> insertList);

    /**
     * 批量更新
     *
     * @param updateList
     * @return
     */
    int updateBatch(List<T> updateList);

    /**
     * 批量删除
     *
     * @param deleteList
     * @return
     */
    int deleteBatch(List<T> deleteList);
}
