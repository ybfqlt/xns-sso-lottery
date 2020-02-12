package com.ns.cloud.controller;


import org.apache.commons.lang.StringUtils;
import com.ns.cloud.constants.CodeConstants;
import com.ns.cloud.constants.StatusConstants;
import com.ns.cloud.dto.Result;
import com.ns.cloud.entities.User;
import com.ns.cloud.service.LoginService;
import com.ns.cloud.service.consumer.RedisClientService;
import com.ns.cloud.utils.CookieUtils;
import com.ns.cloud.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author: xns
 * @Date: 20-1-28 下午7:33
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    RedisClientService redisClientService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            String s = JsonUtils.objToJson(bindingResult.getAllErrors());
            log.error(s);
        }
        if (user != null) {
            loginService.register(user);
        }
        return new Result();
    }


    /**
     * 访问登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest, @RequestParam(required = false) String url, Model model) {
        String token = CookieUtils.getCookieValue(httpServletRequest, "token");
        if (StringUtils.isNotBlank(token)) {
            String name = redisClientService.get(token);
            if (StringUtils.isNotBlank(name)) {
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                } else {//已经登录，url为空
                    model.addAttribute("url", "");
                }
            }
        }
        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }
        return "login";
    }

    /**
     * 登录
     *
     * @param user
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/login")
    public Result login(@Validated @RequestBody User user, @RequestParam(required = false) String url, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BindingResult bindingResult) throws Exception {
        String mes;
        System.out.println(url);
        if (bindingResult.hasErrors()) {
            String s = JsonUtils.objToJson(bindingResult.getAllErrors());
            log.error(s);
        }
        User login = loginService.login(user);
        //登录失败
        if (login == null) {
            return new Result(CodeConstants.CODE_FAIL, "密码错误/不存在此用户,请重新输入");
        } else {
            String token = UUID.randomUUID().toString();
            String json = redisClientService.put(token, login.getUserName(), 60 * 60);
            if (StatusConstants.STATUS_YES.equals(json)) {
                CookieUtils.setCookie(httpServletRequest, httpServletResponse, "token", token, 60 * 60);
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("name",login.getUserName());
                if (StringUtils.isNotBlank(url)) {
                    return new Result<String>(CodeConstants.CODE_REDIRECT, "登陆成功", url);
                }
            } else {
                return new Result(CodeConstants.CODE_FAIL, "网络不给力，请再试一次");
            }
        }
        return new Result(CodeConstants.CODE_SUCCESS, "登陆成功");
    }
}
