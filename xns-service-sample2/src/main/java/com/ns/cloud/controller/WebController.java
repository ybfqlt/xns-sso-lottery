package com.ns.cloud.controller;

import com.ns.cloud.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:28
 */
@Controller
public class WebController {

    private RestTemplate restTemplate;

    private static final String SSO_SERVER_PREFIX="http://localhost:8001";

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @ResponseBody
    @GetMapping("/logout")
    public String Logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return restTemplate.postForObject(SSO_SERVER_PREFIX+"/sso/logout", CookieUtils.getCookieValue(httpServletRequest,"token"),String.class);
    }

    @ResponseBody
    @PostMapping("/slogout")
    public void Slogout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        httpServletRequest.getSession().invalidate();
    }
}
