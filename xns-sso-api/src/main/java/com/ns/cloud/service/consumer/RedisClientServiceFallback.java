package com.ns.cloud.service.consumer;

import com.ns.cloud.dto.Result;
import com.ns.cloud.utils.JsonUtils;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: xns
 * @Date: 20-2-1 下午10:02
 */
@Component
public class RedisClientServiceFallback implements FallbackFactory<RedisClientService> {
    @Override
    public RedisClientService create(Throwable throwable) {
        return new RedisClientService() {
            @Override
            public String put(String key, String value, long seconds) {
               Result result = new Result().no("该key:"+key+"put失败");
               try{
                   return JsonUtils.objToJson(result);
               }catch (Exception e){
                   e.printStackTrace();
               }
               return null;
            }

            @Override
            public String get(String key) {
                Result result = new Result().no("该key:"+key+"没有对应的value,即不存在");
                try{
                    return JsonUtils.objToJson(result);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}
