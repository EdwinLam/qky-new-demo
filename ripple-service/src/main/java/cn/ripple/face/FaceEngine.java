package cn.ripple.face;

import cn.ripple.face.bean.*;
import cn.ripple.face.enums.*;
import cn.ripple.face.util.ConfUtil;
import cn.ripple.face.util.ImageLoader;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 引擎入口工具类
 *
 * @author Jastar·Wang
 * @version 2.0
 * @date 2018/11/30
 */
@Slf4j
public class FaceEngine {

	private  FaceLibrary INSTANCE =  FaceLibrary.INSTANCE;
	private  PointerByReference phEngine = new PointerByReference();

	public  FaceLibrary getInstance() {
		if (INSTANCE == null) {
			INSTANCE = FaceLibrary.INSTANCE;
		}
		return INSTANCE;
	}

	/**
	 * 激活
	 */
	public  void activation(String appId,String appKey) {
		if (INSTANCE == null) {
			throw new RuntimeException("Face Engine is null");
		}
		NativeLong result = INSTANCE.ASFActivation(appId, appKey);

		log.debug("------>engine is activated[{}]!<------", result.longValue());
	}

	/**
	 * 初始化
	 */
	public  void init() {
		int mask = Mask.ASF_FACE_DETECT | Mask.ASF_FACERECOGNITION | Mask.ASF_AGE | Mask.ASF_GENDER
				| Mask.ASF_FACE3DANGLE;
		NativeLong ret = INSTANCE.ASFInitEngine(DetectMode.ASF_DETECT_MODE_IMAGE, OrientPriority.AFD_FSDK_OPF_0_ONLY,
				16, 50, mask, phEngine);
		if (ret.longValue() != ErrorCode.MOK) {
			throw new RuntimeException("engine init error by code :" + ret.longValue());
		}
		log.debug("------>engine init finish!<------");
	}

	/**
	 * 关闭引擎
	 */
	public  void unInit(){
		INSTANCE.ASFUninitEngine(phEngine.getValue());
	}

	/**
	 * 将图片的宽度转换为4的整数倍（官方要求，原因尚不清楚）
	 *
	 * @param src
	 *            原图
	 * @return 新图
	 */
	private  BufferedImage convertImageTo4Times(BufferedImage src) {
		if (src.getWidth() % 4 != 0) {
			return src.getSubimage(0, 0, src.getWidth() - (src.getWidth() % 4), src.getHeight());
		}
		return src;
	}

	/**
	 * 获取引擎版本信息
	 *
	 * @author Jastar Wang
	 * @date 2018/11/30
	 * @version 2.0
	 * @reurn 引擎版本信息
	 */
	public  Version getEngineVersion() {
		return getInstance().ASFGetVersion(phEngine.getValue());
	}

	/**
	 * 检测多张人脸
	 *
	 * @param image
	 *            图片
	 * @author Jastar Wang
	 * @date 2018/11/30
	 * @version 2.0
	 */
	public  MultiFaceInfo detectFaces(BufferedImage image) {
		MultiFaceInfo detectFaces = new MultiFaceInfo();
		image = convertImageTo4Times(image);
		BufferInfo bufferInfo = ImageLoader.getBGRFromFile(image);
		getInstance().ASFDetectFaces(phEngine.getValue(), image.getWidth(), image.getHeight(),
				ColorFormat.ASVL_PAF_RGB24_B8G8R8, bufferInfo.buffer, detectFaces);
		if (detectFaces.haveFace()) {
			int combinedMask = Mask.ASF_AGE | Mask.ASF_GENDER | Mask.ASF_FACE3DANGLE;
			getInstance().ASFProcess(phEngine.getValue(), image.getWidth(), image.getHeight(), ColorFormat.ASVL_PAF_RGB24_B8G8R8, bufferInfo.buffer, detectFaces, combinedMask);
			AgeInfo ageInfo = new AgeInfo();
			getInstance().ASFGetAge(phEngine.getValue(),ageInfo);
			detectFaces.setAges( ageInfo.getAges());
			// 3d
			Face3DAngle face3dAngle = new Face3DAngle();
			getInstance().ASFGetFace3DAngle(phEngine.getValue(), face3dAngle);
			detectFaces.setPitchs(face3dAngle.getPitchs());
			detectFaces.setRolls(face3dAngle.getRolls());
			detectFaces.setYaws(face3dAngle.getYaws());
			detectFaces.setStatuses(face3dAngle.getStatuses());
			// 性别
			GenderInfo genderInfo = new GenderInfo();
			getInstance().ASFGetGender(phEngine.getValue(), genderInfo);
			detectFaces.setGenders(genderInfo.getGenders());
		}
		return detectFaces;
	}

	/**
	 * 提取特征值
	 *
	 * @param faceInfo
	 *            单人脸信息
	 * @param image
	 *            图片
	 * @return 特征值对象
	 * @author Jastar Wang
	 * @date 2018/11/30
	 * @version 2.0
	 */
	public  FaceFeature extractFeature(SingleFaceInfo faceInfo, BufferedImage image) {
		FaceFeature feature = new FaceFeature();
		image = convertImageTo4Times(image);
		BufferInfo bufferInfo = ImageLoader.getBGRFromFile(image);
		getInstance().ASFFaceFeatureExtract(phEngine.getValue(), image.getWidth(), image.getHeight(),
				ColorFormat.ASVL_PAF_RGB24_B8G8R8, bufferInfo.buffer, faceInfo, feature);
		if (feature.getFeatureData() != null && feature.getFeatureData().length > 0) {
			// 注意此处返回的时候重新new了一个新的FaceFeature，此处的目的是对FaceFeature做深度Copy，因为虹软对内存做了一些优化，FaceFeature的内存会被重复使用，如果不做深度copy，反复调用该方法，FaceFeature中的特征数据会被覆盖
			return new FaceFeature(feature.getFeatureData());
		}
		return null;
	}

	/**
	 * 对比特征值
	 *
	 * @param feature1
	 *            特征值1
	 * @param feature2
	 *            特征值2
	 * @return 相似度（置信度）
	 * @author Jastar Wang
	 * @date 2018/11/30
	 * @version 2.0
	 */
	public  float compareFeature(FaceFeature feature1, FaceFeature feature2) {
		FloatByReference similar = new FloatByReference();
		getInstance().ASFFaceFeatureCompare(phEngine.getValue(), feature1, feature2, similar);
		return similar.getValue();
	}


}
