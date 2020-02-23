package com.ns.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ns.cloud.dto.Result;
import com.ns.cloud.entities.Prize;
import com.ns.cloud.service.PrizeService;
import com.ns.cloud.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/prize")
    public Result getPrize(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Prize prize = prizeService.getPrize();
        return new Result<Prize>().success(prize,(String)(httpServletRequest.getSession().getAttribute("userId")));
    }

    @GetMapping("/lottery")
    @HystrixCommand(fallbackMethod = "processBack_Get")
    public Result Lottery(@RequestParam("userId") String userId,@RequestParam("prizeId") Long prizeId){
        Result lottery = prizeService.lottery(userId, prizeId);
        return lottery;
    }

    @GetMapping("/getres")
    public Result getResult(@RequestParam("userId") String userId,@RequestParam("prizeId") Long prizeId){
        Result lotteryResult = recordService.getLotteryResult(userId, prizeId);
        return  lotteryResult;
    }

    public Result processBack_Get(@RequestParam("userId") String userId,@RequestParam("prizeId") Long prizeId){
        return new Result().no("服务器繁忙，请稍后再试");
    }

}
