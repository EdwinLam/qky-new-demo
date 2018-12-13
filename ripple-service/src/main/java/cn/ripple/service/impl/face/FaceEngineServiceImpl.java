package cn.ripple.service.impl.face;

import cn.hutool.core.collection.CollectionUtil;
import cn.ripple.entity.face.Face3DAngle;
import cn.ripple.entity.face.FaceInfo;
import cn.ripple.entity.face.ImageInfo;
import cn.ripple.entity.face.Rect;
import cn.ripple.entity.user.UserInfo;
import cn.ripple.face.FaceEngine;
import cn.ripple.face.bean.FaceFeature;
import cn.ripple.face.bean.MultiFaceInfo;
import cn.ripple.face.bean.SingleFaceInfo;
import cn.ripple.factory.FaceEngineFactory;
import cn.ripple.service.face.FaceEngineService;
import cn.ripple.service.user.UserInfoService;

import com.arcsoft.face.enums.ImageFormat;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Service
public class FaceEngineServiceImpl implements FaceEngineService {

    public final static Logger logger = LoggerFactory.getLogger(FaceEngineServiceImpl.class);

    @Value("${ripple.face.appId}")
    public String appId;

    @Value("${ripple.face.sdkKey}")
    public String sdkKey;

    @Value("${ripple.face.threadPoolSize}")
    public Integer threadPoolSize;

    private LoadingCache<Integer, List<UserInfo>> faceGroupCache;

    private Integer passRate = 80;

    private ExecutorService executorService;


    private GenericObjectPool<FaceEngine> extractFaceObjectPool;
    private GenericObjectPool<FaceEngine> compareFaceObjectPool;

    @Autowired
    private UserInfoService userInfoService;

    @PostConstruct
    public void init() {
        initCache();
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(threadPoolSize);
        poolConfig.setMaxTotal(threadPoolSize);
        poolConfig.setMinIdle(threadPoolSize);
        poolConfig.setLifo(false);
        extractFaceObjectPool = new GenericObjectPool(new FaceEngineFactory(appId, sdkKey), poolConfig);//底层库算法对象池
        compareFaceObjectPool = new GenericObjectPool(new FaceEngineFactory(appId, sdkKey), poolConfig);//底层库算法对象池
    }

    /**f
     * 初始化缓存
     */
    private void initCache() {
        this.faceGroupCache = CacheBuilder
                .newBuilder()
                .maximumSize(100)
                .expireAfterAccess(2, TimeUnit.HOURS)
                .build(new CacheLoader<Integer, List<UserInfo>>() {
                    @Override
                    public List<UserInfo> load(Integer groupId) throws Exception {
                        List<UserInfo> userInfos =userInfoService.getAll();
                        return userInfos;
                    }
                });
    }

    @Override
    public List<FaceInfo> detectFaces(BufferedImage imageBuf) {
        List<FaceInfo> faces = new ArrayList<>();
        FaceEngine faceEngine = null;
        try {
            //获取引擎对象
            faceEngine = extractFaceObjectPool.borrowObject();
            //获取引擎对象
            MultiFaceInfo multiFaceInfo = faceEngine.detectFaces(imageBuf);
            for(SingleFaceInfo singleFaceInfo:multiFaceInfo.getFaces()){
                FaceInfo faceInfo = new FaceInfo();
                // 方位信息
                Rect rect = new Rect();
                rect.setLeft(singleFaceInfo.faceRect.left);
                rect.setRight(singleFaceInfo.faceRect.right);
                rect.setTop(singleFaceInfo.faceRect.top);
                rect.setBottom(singleFaceInfo.faceRect.bottom);
                faceInfo.setRect(rect);
                // 3d相关信息
                Face3DAngle face3DAngle = new Face3DAngle();
                face3DAngle.setRoll(singleFaceInfo.roll);
                face3DAngle.setPitch(singleFaceInfo.pitch);
                face3DAngle.setYaw(singleFaceInfo.yaw);
                face3DAngle.setStatus(singleFaceInfo.status);
                faceInfo.setFace3DAngle(face3DAngle);
                // 年龄信息
                faceInfo.setAge(singleFaceInfo.age);
                faceInfo.setGender(singleFaceInfo.gender);
                faces.add(faceInfo);
                // 提取人脸特征
                FaceFeature faceFeature=faceEngine.extractFeature(singleFaceInfo,imageBuf);
                faceFeature.getFeatureData();
//                faceInfo.setFaceFeature(faceFeature.getFeatureData());

            }
            return  faces;
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                extractFaceObjectPool.returnObject(faceEngine);
            }

        }

        return null;
    }

    /**
     * 人脸特征
     * @param imageBuf
     * @return
     */
    @Override
    public byte[] extractFaceFeature(BufferedImage imageBuf) {
        FaceEngine faceEngine = null;
        try {
            //获取引擎对象
            faceEngine = extractFaceObjectPool.borrowObject();

            //人脸检测得到人脸列表
            MultiFaceInfo multiFaceInfo = faceEngine.detectFaces(imageBuf);
            for(SingleFaceInfo singleFaceInfo:multiFaceInfo.getFaces()){
                FaceFeature faceFeature=faceEngine.extractFeature(singleFaceInfo,imageBuf);
                return faceFeature.getFeatureData();
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                extractFaceObjectPool.returnObject(faceEngine);
            }

        }

        return null;
    }



}
