package com.ns.sso.controller;

import com.ns.sso.dto.Result;
import com.ns.sso.entities.User;
import com.ns.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xns
 * @Date: 20-1-28 下午7:33
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@Validated User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Result("格式错误");
        }
        if(user != null){
            loginService.register(user);
        }
        return new Result<Object>(null);
    }
}
