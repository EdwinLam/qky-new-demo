package cn.thinkjoy.common.service;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import java.util.Map;

public abstract interface IPageService<D extends IBaseDAO, T extends BaseDomain>
{
  public abstract BizData4Page queryPageByDataPerm(String paramString, Map<String, Object> paramMap, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void queryPageByDataPerm(BizData4Page paramBizData4Page);
  
  public abstract void queryPageByDataPerm(IBaseDAO paramIBaseDAO, BizData4Page paramBizData4Page);
  
  public abstract void queryPageByDataPerm(BizData4Page paramBizData4Page, String paramString, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract BizData4Page queryPageByDataPerm(String paramString1, Map<String, Object> paramMap, int paramInt1, int paramInt2, int paramInt3, String paramString2, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract void queryPageByDataPerm(BizData4Page paramBizData4Page, String paramString, SqlOrderEnum paramSqlOrderEnum, Map<String, Object> paramMap);
  
  public abstract void queryPageByDataPerm(IBaseDAO paramIBaseDAO, BizData4Page paramBizData4Page, String paramString, SqlOrderEnum paramSqlOrderEnum, Map<String, Object> paramMap);
  
  public abstract BizData4Page queryPageByDataPerm(IBaseDAO paramIBaseDAO, Map<String, Object> paramMap1, int paramInt1, int paramInt2, int paramInt3, String paramString, SqlOrderEnum paramSqlOrderEnum, Map<String, Object> paramMap2);
}


/* Location:              C:\Users\Administrator\Desktop\thinkjoy-biz-common-0.1.29-SNAPSHOT.jar!\cn\thinkjoy\common\service\IPageService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */