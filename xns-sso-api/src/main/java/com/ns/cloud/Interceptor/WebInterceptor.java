package com.ns.cloud.Interceptor;

import com.ns.cloud.entities.User;
import com.ns.cloud.service.consumer.RedisClientService;
import com.ns.cloud.utils.CookieUtils;
import com.ns.cloud.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:31
 */
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

//    @Autowired
//    private RedisClientService redisClientService;
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = CookieUtils.getCookieValue(request, "token");
//        if (StringUtils.isBlank(token)) {
//            response.sendRedirect("http://localhost:8001/sso/login?url=" + request.getRequestURL());
//        } else {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        HttpSession httpSession = request.getSession();
//        boolean flag = false;
//        String user = (String) httpSession.getAttribute("name");
//        if (user == null) {
//            String token = CookieUtils.getCookieValue(request, "token");
//            if (StringUtils.isNotBlank(token)) {
//                String s = redisClientService.get(token);
//                if (StringUtils.isNotBlank(s)) {
//                    String s1 = redisClientService.get(s);
//                    if (StringUtils.isNotBlank(s1)) {
//                        User user1 = JsonUtils.jsonToPojo(s1,User.class);
//                        httpSession.setAttribute("name",user1.getUserName());
//                        httpSession.setAttribute("userId",user1.getUserId());
//                    }
//                }else{
//                    flag = true;
//                }
//            } else {
//                flag=true;
//            }
//        }else{
//            log.info(LocalDateTime.now()+" 用户名:"+user);
//        }
//        if(flag){
//            response.sendRedirect("http://localhost:8001/sso/login?url=" + request.getRequestURL());
//        }
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//
//    }
}
