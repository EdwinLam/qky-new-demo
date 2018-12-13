package cn.ripple.service.face;


import cn.ripple.entity.face.FaceInfo;
import cn.ripple.face.bean.SingleFaceInfo;

import java.awt.image.BufferedImage;
import java.util.List;

public interface FaceEngineService {

    /**
     * 人脸特征
     * @param imageBuf
     * @return
     */
    byte[] extractFaceFeature(BufferedImage imageBuf);

    List<FaceInfo> detectFaces(BufferedImage imageBuf);

}
