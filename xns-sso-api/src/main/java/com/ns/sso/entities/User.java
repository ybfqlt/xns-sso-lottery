package com.ns.sso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: xns
 * @Date: 20-1-28 下午12:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class User {
    private String userId;

    private String userName;

    private String userPassword;
}
