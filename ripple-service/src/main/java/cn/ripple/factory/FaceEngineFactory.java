package cn.ripple.factory;


import cn.ripple.face.FaceEngine;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @Author: st7251
 * @Date: 2018/10/16 13:47
 */
public class FaceEngineFactory extends BasePooledObjectFactory<FaceEngine> {

    private String appId;
    private String sdkKey;


    public FaceEngineFactory(String appId, String sdkKey) {
        this.appId = appId;
        this.sdkKey = sdkKey;
    }



    @Override
    public FaceEngine create() {
        FaceEngine faceEngine =new FaceEngine();
        faceEngine.init();
        faceEngine.activation(appId,sdkKey);
        return faceEngine;
    }

    @Override
    public PooledObject<FaceEngine> wrap(FaceEngine faceEngine) {
        return new DefaultPooledObject<>(faceEngine);
    }


    @Override
    public void destroyObject(PooledObject<FaceEngine> p) throws Exception {
        FaceEngine faceEngine = p.getObject();
        faceEngine.unInit();
        super.destroyObject(p);
    }
}
