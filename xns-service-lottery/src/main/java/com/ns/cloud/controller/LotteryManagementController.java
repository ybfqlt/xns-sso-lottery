package com.ns.cloud.controller;

import com.ns.cloud.dto.Result;
import com.ns.cloud.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xns
 * @Date: 20-2-22 上午1:24
 */
@RestController
public class LotteryManagementController {

    @Autowired
    PrizeService prizeService;

    @GetMapping("/addtoken")
    public Result generateToken(@RequestParam("prizeId") Long prizeId, @RequestParam("tokenNumber") Integer tokenNumber){
        Result result = prizeService.addLotteryToken(prizeId, tokenNumber);
        return result;
    }
}
