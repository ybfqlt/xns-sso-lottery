package com.ns.cloud.mapper;

import com.ns.cloud.entities.Prize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: xns
 * @Date: 20-2-20 下午10:27
 */
@Mapper
public interface PrizeMapper {

    int updatePrizeStorage(@Param("prizeId") Long prizeId,@Param("version") Long version);


    Prize findByPrizeId(@Param("prizeId") Long prizeId);
}
