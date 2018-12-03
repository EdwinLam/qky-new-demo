package cn.thinkjoy.common.service;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;

public abstract interface IDaoAware<D extends IBaseDAO, T extends BaseDomain>
{
  public abstract D getDao();
}


/* Location:              C:\Users\Administrator\Desktop\thinkjoy-biz-common-0.1.29-SNAPSHOT.jar!\cn\thinkjoy\common\service\IDaoAware.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */