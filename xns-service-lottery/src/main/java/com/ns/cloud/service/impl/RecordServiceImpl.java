package com.ns.cloud.service.impl;

import com.ns.cloud.constants.CodeConstants;
import com.ns.cloud.dto.Result;
import com.ns.cloud.entities.LotteryRecord;
import com.ns.cloud.mapper.RecordMapper;
import com.ns.cloud.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xns
 * @Date: 20-2-22 上午12:59
 */
@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public Result getLotteryResult(String userId, Long prizeId) {
        if(StringUtils.isEmpty(userId)){
            return new Result().no("用户id不能为空");
        }
        if(prizeId == null){
           log.warn("奖品id不能为空");
        }
        LotteryRecord lotteryRecord = recordMapper.selectByUserIdAndPrizeId(userId,prizeId);
        if(lotteryRecord == null){
            return new Result(CodeConstants.CODE_SUCCESS,"开奖中....");
        }
        return new Result(CodeConstants.CODE_SUCCESS,"幸运儿，你抽中了");
    }
}
