package com.ns.sso.service.impl;

import com.ns.sso.constants.StatusConstants;
import com.ns.sso.entities.User;
import com.ns.sso.mapper.UserMapper;
import com.ns.sso.service.LoginService;
import com.ns.sso.service.consumer.RedisClientService;
import com.ns.sso.utils.IdWorker;
import com.ns.sso.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xns
 * @Date: 20-2-1 下午8:35
 */
@Slf4j
@Service
public class LonginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;


    @Autowired
    RedisClientService redisClientService;

    @Override
    public void register(User user) {
        IdWorker idWorker = new IdWorker();
        user.setUserId(String.valueOf(idWorker.nextId()));
        if (userMapper.insert(user)) ;
    }

    /**
     * 登录逻辑
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        String json = redisClientService.get(user.getUserName());

        if (StatusConstants.STATUS_NO.equals(json)) {
            User user1 = userMapper.findByName(user.getUserName());
            if (user1 == null) {
                return null;
            }
            if (user1.getUserPassword().equals(user.getUserPassword())) {
                String json1 = null;
                try {
                    json1 = JsonUtils.objToJson(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                redisClientService.put(user.getUserName(), json1, 60 * 60);
                return user1;
            } else {
                return null;
            }
        } else {
            User user1 = null;
            try {
                user1 = JsonUtils.jsonToPojo(json, User.class);
                if (user.getUserPassword().equals(user1.getUserPassword())) {
                    return user;
                }
            } catch (Exception e) {
                log.warn("熔断:{}", e.getMessage());
            }
        }
        return null;
    }
}
