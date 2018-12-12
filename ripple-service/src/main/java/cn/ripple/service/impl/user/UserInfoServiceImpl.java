package cn.ripple.service.impl.user;

import cn.ripple.dao.user.UserInfoDao;
import cn.ripple.entity.user.UserInfo;
import cn.ripple.service.user.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户接口实现
 * @author Edwin
 */
@Slf4j
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfoDao getRepository() {
        return userInfoDao;
    }
}
