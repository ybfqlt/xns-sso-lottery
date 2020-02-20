package com.ns.cloud.service;

import com.ns.cloud.dto.Result;

/**
 * @Author: xns
 * @Date: 20-2-20 下午9:53
 */
public interface PrizeService {

    public Result lottery(String userId,Long prizeId);
}
