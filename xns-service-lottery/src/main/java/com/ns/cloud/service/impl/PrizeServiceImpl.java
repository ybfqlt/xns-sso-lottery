package com.ns.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ns.cloud.constants.CodeConstants;
import com.ns.cloud.constants.LotteryConstants;
import com.ns.cloud.dto.Result;
import com.ns.cloud.mapper.PrizeMapper;
import com.ns.cloud.mapper.RecordMapper;
import com.ns.cloud.producer.LotteryStockProducer;
import com.ns.cloud.service.PrizeService;
import com.ns.cloud.service.consumer.RedisClientService;
import com.ns.cloud.utils.GenerateToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: xns
 * @Date: 20-2-20 下午9:53
 */
@Slf4j
@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    PrizeMapper prizeMapper;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    GenerateToken generateToken;

    @Autowired
    LotteryStockProducer lotteryStockProducer;

    @Autowired
    RedisClientService redisClientService;


    @Override
    @Transactional
    public Result lottery(String userId, Long prizeId) {
        if(StringUtils.isEmpty(userId)){
            return new Result(CodeConstants.CODE_SUCCESS,"用户id不能为空");
        }
        if(prizeId == null){
            return new Result(CodeConstants.CODE_FAIL,"prizeId不能为空");
        }

        String lotteryToken = generateToken.getListOfOneByToken(prizeId+"");
        if(StringUtils.isEmpty(lotteryToken)){
            log.info("礼物"+prizeId+"已经抽完，请下次抓紧哦");
            return new Result(CodeConstants.CODE_SUCCESS,"对不起，奖品已被抽完，下次抓紧时机哦");
        }

        //异步放入mq修改stock
        sendLotteryMes(userId,prizeId);
        return new Result(CodeConstants.CODE_SUCCESS,"开奖中....");

//        Prize prize = prizeMapper.findByPrizeId(prizeId);
//
//        Boolean setnx = redisClientService.setnx(userId,prizeId+"",60L);
//        if(setnx==null){
//            return new Result().no("网络问题，请稍后再试");
//        }
//        if(!setnx){
//            return new Result().no("访问次数太频繁,请在60秒后再试");
//        }
//
//        int judge = prizeMapper.updatePrizeStorage(prizeId,prize.getVersion());
//        if(judge<=0){
//            return new Result().no("未中奖");
//        }
//
//        Record record = new Record();
//        record.setUserId(userId).setPrizeId(prizeId).setState(1).setCreateTime(LocalDateTime.now());
//        recordMapper.insert(record);
//
//        return new Result(CodeConstants.CODE_SUCCESS,"恭喜你,中了哦!");
    }

    //获取抽奖token之后，异步放入mq中实现礼品库存修改
    @Async
    void sendLotteryMes(String userId, Long prizeId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("prizeId",prizeId);
        jsonObject.put("userId",userId);
        lotteryStockProducer.send(jsonObject);
    }


    @Override
    public Result addLotteryToken(Long prizeId, Integer tokenNumber) {
        if(prizeId == null){
            return new Result().no("奖品id不能为null");
        }
        if(tokenNumber == null){
            return new Result().no("token数量不能为空");
        }
        generateLotteryToken(prizeId,tokenNumber);
        return new Result(CodeConstants.CODE_SUCCESS,"令牌准备完毕");
    }


    //多线程异步修改
    @Async
    void generateLotteryToken(Long prizeId, Integer tokenNumber){
        generateToken.createListToken(LotteryConstants.LOTTERY_TOKEN,prizeId+"",tokenNumber);
    }
}
