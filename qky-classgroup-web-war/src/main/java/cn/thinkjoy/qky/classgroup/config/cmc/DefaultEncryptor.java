package cn.thinkjoy.qky.classgroup.config.cmc;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Auther: Edwin
 * @Date: 2018/12/5 16:55
 * @Description:
 */
public class DefaultEncryptor implements StringEncryptor {
    @Autowired
    private Map<String,String> cmc;

    @Override
    public String encrypt(String message) {
        return message;
    }

    @Override
    public String decrypt(String key) {
        return cmc.get(key);
    }

}
