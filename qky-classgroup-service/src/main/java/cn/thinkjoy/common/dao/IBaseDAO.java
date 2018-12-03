package cn.thinkjoy.common.dao;

import cn.thinkjoy.common.domain.BaseDomain;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface IBaseDAO<T extends BaseDomain>
{
  public abstract int insert(T paramT);
  
  public abstract int update(T paramT);
  
  public abstract int updateMap(@Param("map") Map<String, Object> paramMap);
  
  public abstract int updateByCondition(@Param("update") Map<String, Object> paramMap1, @Param("condition") Map<String, Object> paramMap2);
  
  public abstract int insertMap(@Param("map") Map<String, Object> paramMap);
  
  public abstract int updateNull(T paramT);
  
  public abstract int deleteById(Object paramObject);
  
  public abstract int deleteByIds(List paramList);
  
  public abstract int deleteByCondition(Map<String, Object> paramMap);
  
  public abstract int deleteByProperty(@Param("property") String paramString, @Param("value") Object paramObject);
  
  public abstract T fetch(Object paramObject);
  
  public abstract T findOne(@Param("property") String paramString1, @Param("value") Object paramObject, @Param("orderBy") String paramString2, @Param("sortBy") String paramString3);
  
  public abstract List<T> findList(@Param("property") String paramString1, @Param("value") Object paramObject, @Param("orderBy") String paramString2, @Param("sortBy") String paramString3);
  
  public abstract List<T> findAll(@Param("orderBy") String paramString1, @Param("sortBy") String paramString2);
  
  public abstract List<T> queryPage(@Param("condition") Map<String, Object> paramMap, @Param("offset") int paramInt1, @Param("rows") int paramInt2, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2);
  
  public abstract List<T> queryPage(@Param("condition") Map<String, Object> paramMap1, @Param("offset") int paramInt1, @Param("rows") int paramInt2, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2, @Param("selector") Map<String, Object> paramMap2);
  
  public abstract List<T> like(@Param("condition") Map<String, Object> paramMap, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2);
  
  public abstract List<T> like(@Param("condition") Map<String, Object> paramMap1, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2, @Param("selector") Map<String, Object> paramMap2);
  
  public abstract List<T> queryList(@Param("condition") Map<String, Object> paramMap, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2);
  
  public abstract List<T> queryList(@Param("condition") Map<String, Object> paramMap1, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2, @Param("selector") Map<String, Object> paramMap2);
  
  public abstract T queryOne(@Param("condition") Map<String, Object> paramMap, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2);
  
  public abstract T queryOne(@Param("condition") Map<String, Object> paramMap1, @Param("orderBy") String paramString1, @Param("sortBy") String paramString2, @Param("selector") Map<String, Object> paramMap2);
  
  public abstract int count(Map<String, Object> paramMap);
  
  public abstract Object selectMaxId();
  
  public abstract T updateOrSave(@Param("condition") T paramT, @Param("condition") Long paramLong);
  
  public abstract T selectOne(@Param("condition") String paramString, @Param("condition") Object paramObject);
  
  public abstract List<T> selectList(@Param("condition") String paramString, @Param("condition") Object paramObject);
  
  public abstract Class<T> getEntityClass();
}


/* Location:              C:\Users\Administrator\Desktop\thinkjoy-biz-common-0.1.29-SNAPSHOT.jar!\cn\thinkjoy\common\dao\IBaseDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */