package com.ns.cloud.controller;

import com.ns.cloud.dto.Result;
import com.ns.cloud.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: xns
 * @Date: 20-2-21 上午12:12
 */
@RestController
public class LotteryContoller {

    @Autowired
    PrizeService prizeService;

    @GetMapping("/lottery")
    public Result Lottery(@RequestParam("userId") String userId,@RequestParam("prizeId") Long prizeId){
        System.out.println("ha");
        Result lottery = prizeService.lottery(userId, prizeId);
        return lottery;
    }

}
