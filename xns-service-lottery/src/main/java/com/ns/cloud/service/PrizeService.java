package com.ns.cloud.service;

import com.ns.cloud.dto.Result;
import com.ns.cloud.entities.Prize;

/**
 * @Author: xns
 * @Date: 20-2-20 下午9:53
 */
public interface PrizeService {

    public Result lottery(String userId, Long prizeId);

    public Result addLotteryToken(Long prizeId, Integer tokenNumber);

    public Prize getPrize();

}
