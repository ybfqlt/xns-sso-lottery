package com.ns.sso.controller;

import com.ns.sso.dto.Result;
import com.ns.sso.entities.User;
import com.ns.sso.service.LoginService;
import com.ns.sso.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result register(@Validated @RequestBody User user,BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            String s = JsonUtils.objToJson(bindingResult.getAllErrors());
            return new Result(s);
        }
        if(user != null){
            loginService.register(user);
        }
        return new Result<Object>(null);
    }

    /**
     * 登录
     * @param user
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody User user,BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            String s = JsonUtils.objToJson(bindingResult.getAllErrors());
            return new Result(s);
        }
        User login = loginService.login(user);
        if(login == null){
            return new Result().no("密码错误/不存在此用户");
        }else{
            return new Result("登陆成功");
        }
    }
}
