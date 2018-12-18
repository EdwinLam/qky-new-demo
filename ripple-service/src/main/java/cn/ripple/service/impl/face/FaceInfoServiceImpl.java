package cn.ripple.service.impl.face;

import cn.ripple.dao.face.FaceInfoDao;
import cn.ripple.entity.face.FaceInfo;
import cn.ripple.service.face.FaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * 用户接口实现
 * @author Edwin
 */
@Slf4j
@Service
@Transactional
public class FaceInfoServiceImpl implements FaceInfoService {

    @Autowired
    private FaceInfoDao faceInfoDao;

    @Override
    public FaceInfoDao getRepository() {
        return faceInfoDao;
    }

}
