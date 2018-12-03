package cn.thinkjoy.common.service;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import java.util.List;
import java.util.Map;

public abstract interface IBaseService<D extends IBaseDAO, T extends BaseDomain>
{
  public abstract D getDao();
  
  public abstract int add(T paramT);
  
  public abstract int edit(T paramT);
  
  public abstract int delete(Object paramObject);
  
  public abstract T view(Object paramObject);
  
  public abstract List<T> listByPage(Map<String, Object> paramMap, int paramInt1, int paramInt2);
  
  public abstract List<T> listByPage(Map<String, Object> paramMap, int paramInt1, int paramInt2, String paramString, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract int insert(T paramT);
  
  public abstract int update(T paramT);
  
  public abstract int insertMap(Map<String, Object> paramMap);
  
  public abstract int updateMap(Map<String, Object> paramMap);
  
  public abstract int updateNull(T paramT);
  
  public abstract int deleteById(Object paramObject);
  
  public abstract int deleteByIds(List paramList);
  
  public abstract int deleteByCondition(Map<String, Object> paramMap);
  
  public abstract int deleteByProperty(String paramString, Object paramObject);
  
  public abstract T fetch(Object paramObject);
  
  public abstract T findOne(String paramString, Object paramObject);
  
  public abstract List<T> findList(String paramString, Object paramObject);
  
  public abstract List<T> findList(String paramString1, Object paramObject, String paramString2, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract List<T> findAll();
  
  public abstract List<T> findAll(String paramString, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract List<T> queryPage(Map<String, Object> paramMap, int paramInt1, int paramInt2);
  
  public abstract List<T> queryPage(Map<String, Object> paramMap, int paramInt1, int paramInt2, String paramString, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract List<T> queryPage(Map<String, Object> paramMap1, int paramInt1, int paramInt2, String paramString, SqlOrderEnum paramSqlOrderEnum, Map<String, Object> paramMap2);
  
  public abstract List<T> like(Map<String, Object> paramMap);
  
  public abstract List<T> like(Map<String, Object> paramMap, String paramString, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract List<T> like(Map<String, Object> paramMap1, String paramString, SqlOrderEnum paramSqlOrderEnum, Map<String, Object> paramMap2);
  
  public abstract List<T> queryList(Map<String, Object> paramMap, String paramString1, String paramString2);
  
  public abstract List<T> queryList(Map<String, Object> paramMap1, String paramString1, String paramString2, Map<String, Object> paramMap2);
  
  public abstract T queryOne(Map<String, Object> paramMap);
  
  public abstract T queryOne(Map<String, Object> paramMap, String paramString, SqlOrderEnum paramSqlOrderEnum);
  
  public abstract T queryOne(Map<String, Object> paramMap1, String paramString, SqlOrderEnum paramSqlOrderEnum, Map<String, Object> paramMap2);
  
  public abstract int count(Map<String, Object> paramMap);
  
  public abstract Object selectMaxId();
  
  public abstract void updateOrSave(T paramT, Object paramObject);
  
  public abstract T selectOne(String paramString, Object paramObject);
  
  public abstract List<T> selectList(String paramString, Object paramObject);
  
  public abstract int updateByCondition(Map<String, Object> paramMap1, Map<String, Object> paramMap2);
}


/* Location:              C:\Users\Administrator\Desktop\thinkjoy-biz-common-0.1.29-SNAPSHOT.jar!\cn\thinkjoy\common\service\IBaseService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */