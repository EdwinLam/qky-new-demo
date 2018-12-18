package cn.ripple.dao.face;

import cn.ripple.dao.BaseDao;
import cn.ripple.entity.face.FaceInfo;

import java.util.List;

/**
 * 用户数据处理层
 * @author Edwin
 */
public interface FaceInfoDao extends BaseDao<FaceInfo,String> {
    List<FaceInfo> findByGroupId(Long groupId);
}
