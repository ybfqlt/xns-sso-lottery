package com.xns.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:28
 */
public class WebController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
