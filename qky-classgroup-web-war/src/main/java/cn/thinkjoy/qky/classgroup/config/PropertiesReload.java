package cn.thinkjoy.qky.classgroup.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: Edwin
 * @Date: 2018/12/5 14:37
 * @Description:
 */
public class PropertiesReload extends PropertyPlaceholderConfigurer {
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props) throws BeansException {
        Properties prop = this.loadProp();
        super.processProperties(beanFactory, prop);
    }

    public Properties loadProp() {
        Properties prop = new Properties();
        Map<String, Object> map = new HashMap<>();
        map = this.loadZk();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            prop.put(entry.getKey(), entry.getValue());
        }
        return prop;
    }

    /**
     * 链接zookeeper，并且加载所有节点的信息，这里会用递归去读取所有节点的信息，
     * 为了方便读取，节点最多为两个节点，比如：
     * root1
     * node1
     * node2
     * root2
     * node3
     * node4
     * 每个节点都可以存储信息，这样定义完全足够一个项目使用了。
     * 节点存储信息格式： key:value;
     * key1:value1;
     * key2:value2;
     * 提示：为了多个项目公用同一个zookeeper，可以一个项目一个节点，指定的项目去加载指定的节点
     * 、 这里是刚入手zk，就写的自己能看的懂的。
     *
     * @return
     */
    public Map<String, Object> loadZk() {
        Map<String, Object> map = new HashMap<>();
        String connectString = "192.168.111.129:2181";
        int sessionTimeout = 50000;
        Watcher watcher = new Watcher() {
            //监控所有被触发的事件
            @Override
            public void process(WatchedEvent watchedevent) {
                System.out.println("已经触发了：" + watchedevent.getType() + "事件");
            }
        };
        try {
            ZooKeeper zk = new ZooKeeper(connectString, sessionTimeout, watcher);
            loadZkInfo(map, zk);//加载节点信息
        } catch (IOException e) {
            System.err.println("load zookeeper fail IOException,error info:" + e.getMessage());
        } catch (KeeperException e) {
            System.err.println("load zookeeper fail KeeperException,error info:" + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("load zookeeper fail InterruptedException,error info:" + e.getMessage());
        }
        return map;
    }

    public void loadZkInfo(Map<String, Object> map, ZooKeeper zk) throws KeeperException, InterruptedException {
        List<String> listRoot = zk.getChildren("/", false);//加载主节点信息
        for (String root : listRoot) {
            String rootInfo = new String(zk.getData("/" + root, false, null));
            String[] arrayRootInfo = rootInfo.split(";");
            for (String arrayRootInfoF : arrayRootInfo) {
                if (arrayRootInfoF.split(":").length > 1)
                    map.put(arrayRootInfoF.split(":")[0], arrayRootInfoF.split(":")[1]);
            }
            //加载子节点信息
            List<String> listNode = zk.getChildren("/" + root, false);//加载子节点信息
            for (String node : listNode) {
                rootInfo = new String(zk.getData("/" + root + "/" + node, false, null));
                arrayRootInfo = rootInfo.split(";");
                for (String arrayRootInfoF : arrayRootInfo) {
                    if (arrayRootInfoF.split(":").length > 1)
                        map.put(arrayRootInfoF.split(":")[0], arrayRootInfoF.split(":")[1]);
                }
            }
        }
    }

}
