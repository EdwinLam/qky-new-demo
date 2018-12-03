//package cn.thinkjoy.qky.classgroup.common;
//
//import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
//import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
//
///**
// * zookeeper动态配置客户端测试类
// * <p/>
// * Created by Simon on 2015/10/21.
// */
//public class ZkConfigClient {
//
//    private static DynConfigClient dynConfigClient;
//
//    private static DynConfigClient getDynConfigClient() {
//        if (dynConfigClient == null) {
//            dynConfigClient = DynConfigClientFactory.getClient();
//        }
//        return dynConfigClient;
//    }
//
//    /**
//     * 获取zookeeper动态配置值
//     *
//     * @param group 分组名称(详细见CMC配置)
//     * @param node  节点名称(详细见CMC配置)
//     * @return
//     */
//    public static String getZkConfig(String group, String node) {
//        try {
//            return getDynConfigClient().getConfig(group, node);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        try {
//            System.out.println(getDynConfigClient().getNodes("/"));
//            System.out.println(getDynConfigClient().getConfig("common", "node1"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
