//
//package cn.thinkjoy.qky.classgroup.common;
//
//import cn.thinkjoy.cloudstack.context.CloudContextFactory;
//import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
//import cn.thinkjoy.cloudstack.dynconfig.IChangeListener;
//import cn.thinkjoy.cloudstack.dynconfig.domain.Configuration;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
///**
// * 系统配置信息
// * <p>
// * Created by Simon on 2015/10/22.
// * modify by tengen on 2016/08/20
// */
//public class SysConfig {
//    private static String qkyWebDomain = null;
//    private static String myWebDomain = null;
//
//
//    /**
//     * 获取全课云主平台前台的域名配置。对应zk路径：/configs/qky/qky/common/DOMAIN
//     *
//     * @return
//     */
//    public static String getQkyWebDomain() {
//        if (qkyWebDomain == null) {
//            try {
//                qkyWebDomain = DynConfigClientFactory.getClient().getConfig("qky", "qky", "common", "DOMAIN");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            DynConfigClientFactory.getClient().registerListeners("qky", "qky", "common", "DOMAIN", new IChangeListener() {
//                @Override
//                public Executor getExecutor() {
//                    return Executors.newSingleThreadExecutor();
//                }
//
//                @Override
//                public void receiveConfigInfo(final Configuration configuration) {
//                    getExecutor().execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            qkyWebDomain = configuration.getConfig();
//                        }
//                    });
//                }
//            });
//        }
//        return qkyWebDomain;
//    }
//
//    /**
//     * 获取我的（即当前应用）域名配置。对应zk路径：/configs/#product_code#/#name#/common/DOMAIN
//     * 其中#product_code#和#name#请参照metadata.properties配置文件里对应指定的值
//     *
//     * @return
//     */
//    public static String getMyWebDomain() {
//        if (myWebDomain == null) {
//            myWebDomain = ZkConfigClient.getZkConfig("common", "DOMAIN");
//
//            DynConfigClientFactory.getClient().registerListeners(CloudContextFactory.getCloudContext().getProductCode(),
//                    CloudContextFactory.getCloudContext().getApplicationName(),
//                    "common", "DOMAIN", new IChangeListener() {
//                        @Override
//                        public Executor getExecutor() {
//                            return Executors.newSingleThreadExecutor();
//                        }
//
//                        @Override
//                        public void receiveConfigInfo(final Configuration configuration) {
//                            getExecutor().execute(new Runnable() {
//                                @Override
//                                public void run() {
//                                    myWebDomain = configuration.getConfig();
//                                }
//                            });
//                        }
//                    });
//        }
//        return myWebDomain;
//    }
//
//}
