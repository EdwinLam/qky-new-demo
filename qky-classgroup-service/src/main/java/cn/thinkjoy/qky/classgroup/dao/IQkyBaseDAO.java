package cn.thinkjoy.qky.classgroup.dao;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tengen on 2016/1/14.
 */
public interface IQkyBaseDAO<T extends BaseDomain> extends IBaseDAO<T> {

    /**
     * 批量插入
     *
     * @param insertList
     * @return
     */
    int insertBatch(@Param("insertList") List<T> insertList);

    /**
     * 批量更新
     *
     * @param updateList
     * @return
     */
    int updateBatch(@Param("updateList") List<T> updateList);
}
