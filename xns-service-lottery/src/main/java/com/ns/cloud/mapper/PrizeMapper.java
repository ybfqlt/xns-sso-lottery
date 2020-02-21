package com.ns.cloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: xns
 * @Date: 20-2-20 下午10:27
 */
@Mapper
public interface PrizeMapper {

    @Update("update prize set prize_mount=prize_mount-1 where prize_id = #{prizeId} and prize_mount > 0")
    int updatePrizeStorage(@Param("prizeId") Long prizeId);
}
