package cn.thinkjoy.qky.classgroup.common.utils;

import java.util.UUID;

/**
 * Created by tengen on 2016/7/19.
 */
public class IDUtils {

    /**
     * uuid 32位
     *
     * @return
     */
    public static String makeUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
