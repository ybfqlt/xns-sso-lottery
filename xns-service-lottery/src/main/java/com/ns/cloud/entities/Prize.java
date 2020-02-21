package com.ns.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: xns
 * @Date: 20-2-21 上午11:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class Prize {
    private Long prizeId;

    private String prizeName;

    private  Integer prizeMount;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private Long version;

}
