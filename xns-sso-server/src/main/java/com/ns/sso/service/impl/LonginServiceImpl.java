package com.ns.sso.service.impl;

import com.ns.sso.entities.User;
import com.ns.sso.mapper.UserMapper;
import com.ns.sso.service.LoginService;
import com.ns.sso.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xns
 * @Date: 20-2-1 下午8:35
 */
@Service
public class LonginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void register(User user) {
        IdWorker idWorker = new IdWorker();
        user.setUserId(String.valueOf(idWorker.nextId()));
        if(userMapper.insert(user));
    }
}
