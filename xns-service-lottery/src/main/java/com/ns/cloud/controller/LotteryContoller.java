package com.ns.cloud.controller;

import com.ns.cloud.dto.Result;
import com.ns.cloud.service.PrizeService;
import com.ns.cloud.service.RecordService;
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

    @Autowired
    RecordService recordService;

    @GetMapping("/lottery")
    public Result Lottery(@RequestParam("userId") String userId,@RequestParam("prizeId") Long prizeId){
        Result lottery = prizeService.lottery(userId, prizeId);
        return lottery;
    }

    @GetMapping("/getres")
    public Result getResult(@RequestParam("userId") String userId,@RequestParam("prizeId") Long prizeId){
        Result lotteryResult = recordService.getLotteryResult(userId, prizeId);
        return  lotteryResult;
    }

}
