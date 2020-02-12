package com.ns.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:28
 */
@Controller
public class WebController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @ResponseBody
    @GetMapping("/logout")
    public String Logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return null;
    }
}
