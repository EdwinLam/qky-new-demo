package cn.ripple.service.face;


import cn.ripple.dao.face.ImageInfo;
import cn.ripple.entity.face.FaceInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FaceEngineService {

    List<FaceInfo> detectFaces(ImageInfo imageInfo);

    List<FaceInfo> compareFaceFeature(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException;

    /**
     * 增加人脸到cache
     * @param groupId
     * @param faceUserInfo
     * @throws ExecutionException
     */
    void addFaceToCache(Integer groupId, FaceInfo faceUserInfo) throws ExecutionException;
}
