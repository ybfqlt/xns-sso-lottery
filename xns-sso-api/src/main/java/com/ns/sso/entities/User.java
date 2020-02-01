package com.ns.sso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: xns
 * @Date: 20-1-28 下午12:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class User {


    private String userId;

    @NotBlank
    @Length(min = 6, max = 20)
    private String userName;

    @NotBlank
    @Length(min = 8, max = 25)
    private String userPassword;
}
