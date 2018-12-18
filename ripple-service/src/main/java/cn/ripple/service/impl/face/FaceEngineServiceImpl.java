package cn.ripple.service.impl.face;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.ripple.dao.face.FaceInfoDao;
import cn.ripple.dao.face.ImageInfo;
import cn.ripple.entity.face.FaceInfo;
import cn.ripple.factory.FaceEngineFactory;
import cn.ripple.service.face.FaceEngineService;
import cn.ripple.service.user.UserInfoService;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.ImageFormat;
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
    private FaceInfoDao faceInfoDao;

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
        extractFaceObjectPool = new GenericObjectPool(new FaceEngineFactory(appId, sdkKey, FunctionConfiguration.builder().supportFaceDetect(true).supportFaceRecognition(true).supportAge(true).supportGender(true).build()), poolConfig);//底层库算法对象池
        compareFaceObjectPool = new GenericObjectPool(new FaceEngineFactory(appId, sdkKey, FunctionConfiguration.builder().supportFaceRecognition(true).build()), poolConfig);//底层库算法对象池

    }

    /**
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
                        List<FaceInfo> faceInfos = faceInfoDao.findByGroupId(groupId.longValue());
                        return faceInfos;
                    }
                });
    }


    private int plusHundred(Float value) {
        BigDecimal target = new BigDecimal(value);
        BigDecimal hundred = new BigDecimal(100f);
        return target.multiply(hundred).intValue();

    }

    @Override
    public void addFaceToCache(Integer groupId, FaceInfo faceInfo) throws ExecutionException {
        List<FaceInfo> userFaceInfoList = faceGroupCache.get(groupId);
        userFaceInfoList.add(faceInfo);
    }


    @Override
    public List<FaceInfo> detectFaces(ImageInfo imageInfo){
        FaceEngine faceEngine = null;
        List<FaceInfo> faceInfos = new ArrayList<>();
        try {
            //获取引擎对象
            faceEngine = extractFaceObjectPool.borrowObject();
            //人脸检测得到人脸列表
            List<com.arcsoft.face.FaceInfo> faceInfoList = new ArrayList<>();
            //人脸检测
            faceEngine.detectFaces(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
            faceEngine.process(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList, FunctionConfiguration.builder().supportAge(true).supportGender(true).build());
            List<GenderInfo> genderInfoList = new ArrayList<>();
            //性别提取
            faceEngine.getGender(genderInfoList);
            //年龄提取
            List<AgeInfo> ageInfoList = new ArrayList<>();
            faceEngine.getAge(ageInfoList);
            for (int i = 0; i <faceInfoList.size() ; i++) {
                FaceInfo faceInfo = new FaceInfo();
                com.arcsoft.face.FaceInfo sourceFaceInfo = faceInfoList.get(i);
                faceInfo.setAge(ageInfoList.get(i).getAge());
                faceInfo.setGender(genderInfoList.get(i).getGender());
                faceInfo.setRectTop(sourceFaceInfo.getRect().getTop());
                faceInfo.setRectTop(sourceFaceInfo.getRect().getBottom());
                faceInfo.setRectTop(sourceFaceInfo.getRect().getLeft());
                faceInfo.setRectTop(sourceFaceInfo.getRect().getRight());
                faceInfo.setFaceOrient(sourceFaceInfo.getOrient());
                FaceFeature faceFeature = new FaceFeature();
                //提取人脸特征
                faceEngine.extractFaceFeature(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(i), faceFeature);
                faceInfo.setFaceFeature(faceFeature.getFeatureData());
                faceInfo.setToken(DigestUtil.md5Hex(faceFeature.getFeatureData()));
                faceInfos.add(faceInfo);
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                extractFaceObjectPool.returnObject(faceEngine);
            }
        }
        return faceInfos;

    }


    @Override
    public List<FaceInfo> compareFaceFeature(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException {
        List<FaceInfo> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表

        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature);
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


        public CompareFaceTask(List<FaceInfo> faceInfoList, FaceFeature targetFaceFeature) {
            this.faceInfoList = faceInfoList;
            this.targetFaceFeature = targetFaceFeature;
        }

        @Override
        public List<FaceInfo> call() {
            FaceEngine faceEngine = null;
            List<FaceInfo> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表
            try {
                faceEngine = compareFaceObjectPool.borrowObject();
                for (FaceInfo faceInfo : resultFaceInfoList) {
                    FaceFeature sourceFaceFeature = new FaceFeature();
                    sourceFaceFeature.setFeatureData(faceInfo.getFaceFeature());
                    FaceSimilar faceSimilar = new FaceSimilar();
                    faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
                    Integer similarValue = plusHundred(faceSimilar.getScore());//获取相似值
                    if (similarValue > passRate) {//相似值大于配置预期，加入到识别到人脸的列表
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


}
