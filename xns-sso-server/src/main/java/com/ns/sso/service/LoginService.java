package com.ns.sso.service;

import com.ns.sso.entities.User;

/**
 * @Author: xns
 * @Date: 20-2-1 下午8:35
 */
public interface LoginService {
    void register(User user);

    User login(User user) throws Exception;
}
