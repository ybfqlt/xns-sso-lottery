package com.ns.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: xns
 * @Date: 20-2-21 上午12:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class LotteryRecord {
    private Long prizeId;

    private String userId;

    private Integer state;

    private LocalDateTime createTime;
}
