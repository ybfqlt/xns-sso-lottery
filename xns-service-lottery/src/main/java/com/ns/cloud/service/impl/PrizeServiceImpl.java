package com.ns.cloud.service.impl;

import com.ns.cloud.dto.Result;
import com.ns.cloud.entities.Record;
import com.ns.cloud.mapper.PrizeMapper;
import com.ns.cloud.mapper.RecordMapper;
import com.ns.cloud.service.PrizeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    @Transactional
    public Result lottery(String userId, Long prizeId) {
        if(StringUtils.isEmpty(userId)){
            return new Result(200,"用户id不能为空");
        }
        if(prizeId == null){
            return new Result(500,"prizeId不能为空");
        }
        int judge = prizeMapper.updatePrizeStorage(prizeId);
        if(judge<=0){
            return new Result().no("未中奖");
        }

        Record record = new Record();
        record.setUserId(userId).setPrizeId(prizeId).setState(1).setCreateTime(LocalDateTime.now());
        recordMapper.insert(record);

        return null;
    }
}
