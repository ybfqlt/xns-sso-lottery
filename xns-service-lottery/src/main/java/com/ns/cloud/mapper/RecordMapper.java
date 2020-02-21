package com.ns.cloud.mapper;

import com.ns.cloud.entities.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: xns
 * @Date: 20-2-21 上午12:28
 */
@Mapper
public interface RecordMapper {

    public int insert(@Param("record") Record record);
}
