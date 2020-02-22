package com.ns.cloud.service;

import com.ns.cloud.dto.Result;

/**
 * @Author: xns
 * @Date: 20-2-22 上午12:59
 */
public interface RecordService {

    /**
     * 获取抽奖结果
     * @param userId
     * @param prizeId
     * @return
     */
    public Result getLotteryResult(String userId,Long prizeId);
}
