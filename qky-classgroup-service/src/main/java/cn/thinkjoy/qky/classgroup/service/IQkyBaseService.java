package cn.thinkjoy.qky.classgroup.service;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.qky.classgroup.dao.IQkyBaseDAO;

/**
 * Created by tengen on 2016/1/14.
 */
public interface IQkyBaseService<D extends IQkyBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>, IPageService<D, T>, IBatch<T> {

}
