package com.ns.cloud.utils;

import com.ns.cloud.service.consumer.RedisClientService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: xns
 * @Date: 20-2-21 下午4:03
 */
@Component
public class GenerateToken {

    @Autowired
    RedisClientService redisClientService;

    public String createToken(String keyPrefix,String redisValue,Long time){
        if(StringUtils.isEmpty(redisValue)){
            new Exception("redisValue Not nul");
        }
        String token = keyPrefix + UUID.randomUUID().toString().replace("-","");
        redisClientService.put(token,redisValue,time);
        return token;
    }

    public void createListToken(String keyPrefix,String redisKey,Long tokeNumber){
        List<String> tokenList = getListToken(keyPrefix,tokeNumber);
        redisClientService.setList(redisKey,tokenList);
    }

    public List<String> getListToken(String keyPrefix,Long tokeNumber){
        List<String> tokenList = new ArrayList<>();
        for(int i=0;i<tokeNumber;i++){
            String token = keyPrefix+UUID.randomUUID().toString().replace("-","");
            tokenList.add(token);
        }
        return tokenList;
    }

    public String getListOfOneByToken(String key){
        String value = (String)redisClientService.getRetmp().opsForList().leftPop(key);
        return value;
    }
}
