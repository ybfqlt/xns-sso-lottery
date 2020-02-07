package com.ns.cloud.service;

import com.ns.cloud.entities.User;

/**
 * @Author: xns
 * @Date: 20-2-1 下午8:35
 */
public interface LoginService {
    void register(User user);

    User login(User user) throws Exception;
}
