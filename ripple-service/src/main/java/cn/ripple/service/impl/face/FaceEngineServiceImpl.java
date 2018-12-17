package cn.ripple.service.impl.face;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.ripple.entity.face.FaceInfo;
import cn.ripple.face.FaceEngine;
import cn.ripple.face.bean.FaceFeature;
import cn.ripple.face.bean.MultiFaceInfo;
import cn.ripple.face.bean.SingleFaceInfo;
import cn.ripple.factory.FaceEngineFactory;
import cn.ripple.service.face.FaceEngineService;
import cn.ripple.service.face.FaceInfoService;
import cn.ripple.service.user.UserInfoService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@Service
public class FaceEngineServiceImpl implements FaceEngineService {

    public final static Logger logger = LoggerFactory.getLogger(FaceEngineServiceImpl.class);

    @Value("${ripple.face.appId}")
    public String appId;

    @Value("${ripple.face.sdkKey}")
    public String sdkKey;

    @Value("${ripple.face.threadPoolSize}")
    public Integer threadPoolSize;

    private LoadingCache<Integer, List<FaceInfo>> faceGroupCache;

    private Integer passRate = 80;

    private ExecutorService executorService;


    private GenericObjectPool<FaceEngine> extractFaceObjectPool;
    private GenericObjectPool<FaceEngine> compareFaceObjectPool;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private FaceInfoService faceInfoService;

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
                .build(new CacheLoader<Integer, List<FaceInfo>>() {
                    @Override
                    public List<FaceInfo> load(Integer groupId) {
                        List<FaceInfo> faceInfos =faceInfoService.getAll();
                        return faceInfos;
                    }
                });
    }

    public void addFaceToCache(Integer groupId, FaceInfo faceUserInfo) throws ExecutionException {
        List<FaceInfo> userFaceInfoList = faceGroupCache.get(groupId);
        userFaceInfoList.add(faceUserInfo);
    }

    /**
     * 识别图中人脸
     * @param imageBuf
     * @return
     */
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
                faceInfo.setRectLeft(singleFaceInfo.faceRect.left);
                faceInfo.setRectRight(singleFaceInfo.faceRect.right);
                faceInfo.setRectTop(singleFaceInfo.faceRect.top);
                faceInfo.setRectBottom(singleFaceInfo.faceRect.bottom);
                // 3d相关信息
                faceInfo.setFace3dRoll(singleFaceInfo.roll);
                faceInfo.setFace3dPitch(singleFaceInfo.pitch);
                faceInfo.setFace3dYaw(singleFaceInfo.yaw);
                faceInfo.setFace3dStatus(singleFaceInfo.status);
                // 年龄信息
                faceInfo.setAge(singleFaceInfo.age);
                faceInfo.setGender(singleFaceInfo.gender);
                faces.add(faceInfo);
                // 提取人脸特征
                FaceFeature faceFeature=faceEngine.extractFeature(singleFaceInfo,imageBuf);
                faceFeature.getFeatureData();
                faceInfo.setFaceFeature(faceFeature.getFeatureData());
                faceInfo.setToken(DigestUtil.md5Hex(faceFeature.getFeatureData()));
//                faceInfoService.save(faceInfo);
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

    public List<FaceInfo> compareFaceFeature(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException {
        List<FaceInfo> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表
        FaceFeature targetFaceFeature = new FaceFeature(faceFeature);
        List<FaceInfo> faceInfoList = faceGroupCache.get(groupId);//从缓存中提取人脸库

        List<List<FaceInfo>> faceUserInfoPartList = Lists.partition(faceInfoList, 1000);//分成1000一组，多线程处理
        CompletionService<List<FaceInfo>> completionService = new ExecutorCompletionService(executorService);
        for (List<FaceInfo> part : faceUserInfoPartList) {
            completionService.submit(new CompareFaceTask(part, targetFaceFeature));
        }
        for (int i = 0; i < faceUserInfoPartList.size(); i++) {
            List<FaceInfo> faceUserInfoList = completionService.take().get();
            if (CollectionUtil.isNotEmpty(faceInfoList)) {
                resultFaceInfoList.addAll(faceUserInfoList);
        }
        }

        resultFaceInfoList.sort((h1, h2) -> h2.getSimilarValue().compareTo(h1.getSimilarValue()));//从大到小排序

        return resultFaceInfoList;
    }


    private class CompareFaceTask implements Callable<List<FaceInfo>> {

        private List<FaceInfo> faceInfoList;
        private FaceFeature targetFaceFeature;


        public CompareFaceTask(List<FaceInfo> faceUserInfoList, FaceFeature targetFaceFeature) {
            this.faceInfoList = faceUserInfoList;
            this.targetFaceFeature = targetFaceFeature;
        }

        @Override
        public List<FaceInfo> call() {
           FaceEngine faceEngine = null;
            List<FaceInfo> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表
            try {
                faceEngine = compareFaceObjectPool.borrowObject();

                for (FaceInfo faceInfo : faceInfoList) {
                    FaceFeature sourceFaceFeature = new FaceFeature(faceInfo.getFaceFeature());
                    Float faceSimilar = faceEngine.compareFeature(new FaceFeature(targetFaceFeature.getFeatureData()), sourceFaceFeature);
                    Integer similarValue = plusHundred(faceSimilar);//获取相似值
                    if (similarValue > passRate) {//相似值大于配置预期，加入到识别到人脸的列表
                        faceInfo.setSimilarValue(faceSimilar);
                        resultFaceInfoList.add(faceInfo);
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            } finally {
                if (faceEngine != null) {
                    compareFaceObjectPool.returnObject(faceEngine);
                }
            }

            return resultFaceInfoList;
        }

    }

    private int plusHundred(Float value) {
        BigDecimal target = new BigDecimal(value);
        BigDecimal hundred = new BigDecimal(100f);
        return target.multiply(hundred).intValue();

    }



}
