package com.ns.cloud.mapper;

import com.ns.cloud.entities.LotteryRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: xns
 * @Date: 20-2-21 上午12:28
 */
@Mapper
public interface RecordMapper {

    public int insert(@Param("lotteryRecord") LotteryRecord lotteryRecord);

    LotteryRecord selectByUserIdAndPrizeId(@Param("userId") String userId, @Param("prizeId")Long prizeId);
}
