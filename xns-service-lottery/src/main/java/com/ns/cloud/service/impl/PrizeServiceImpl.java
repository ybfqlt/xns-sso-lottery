package com.ns.cloud.service.impl;

import com.ns.cloud.constants.CodeConstants;
import com.ns.cloud.constants.LotteryConstants;
import com.ns.cloud.dto.Result;
import com.ns.cloud.entities.Prize;
import com.ns.cloud.entities.Record;
import com.ns.cloud.mapper.PrizeMapper;
import com.ns.cloud.mapper.RecordMapper;
import com.ns.cloud.service.PrizeService;
import com.ns.cloud.service.consumer.RedisClientService;
import com.ns.cloud.utils.GenerateToken;
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
@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    PrizeMapper prizeMapper;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    GenerateToken generateToken;

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

        Prize prize = prizeMapper.findByPrizeId(prizeId);

        Boolean setnx = redisClientService.setnx(userId,prizeId+"",60L);
        if(setnx==null){
            return new Result().no("网络问题，请稍后再试");
        }
        if(!setnx){
            return new Result().no("访问次数太频繁,请在60秒后再试");
        }

        int judge = prizeMapper.updatePrizeStorage(prizeId,prize.getVersion());
        if(judge<=0){
            return new Result().no("未中奖");
        }

        Record record = new Record();
        record.setUserId(userId).setPrizeId(prizeId).setState(1).setCreateTime(LocalDateTime.now());
        recordMapper.insert(record);

        return new Result(CodeConstants.CODE_SUCCESS,"恭喜你,中了哦!");
    }

    @Override
    public Result addLotteryToken(Long prizeId, Long tokenNumber) {
        return null;
    }

}
