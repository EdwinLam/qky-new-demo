package cn.ripple.service.impl;

import cn.ripple.dao.UserDao;
import cn.ripple.entity.User;
import cn.ripple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户接口实现
 * @author Edwin
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDao getRepository() {
        return userDao;
    }
}