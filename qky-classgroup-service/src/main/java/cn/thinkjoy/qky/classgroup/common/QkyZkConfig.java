//package cn.thinkjoy.qky.classgroup.common;
//
//import cn.thinkjoy.cloudstack.sz.ZookeeperConfigurer;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.core.env.ConfigurablePropertyResolver;
//
///**
// * Created by tengen on 2016/12/2.
// */
//public class QkyZkConfig extends ZookeeperConfigurer {
//    private ConfigurablePropertyResolver propertyResolver;
//
//    @Override
//    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
//                                     final ConfigurablePropertyResolver propertyResolver) throws BeansException {
//        super.processProperties(beanFactoryToProcess, propertyResolver);
//
//        this.propertyResolver = propertyResolver;
//    }
//
//    @Override
//    public Object getProperty(String key) {
//        return this.propertyResolver.getProperty(key);
//    }
//}
