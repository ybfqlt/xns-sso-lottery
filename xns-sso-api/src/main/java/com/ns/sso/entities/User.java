package com.ns.sso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "用户名不能为空")
    @Size(min=6,max=20,message = "用户名必须在6-20个字符之间")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 25,message = "密码长度必须在8~25之间")
    private String userPassword;
}
