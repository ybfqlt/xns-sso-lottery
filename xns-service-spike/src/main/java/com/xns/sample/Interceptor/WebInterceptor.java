package com.xns.sample.Interceptor;

import com.ns.sso.entities.User;
import com.ns.sso.service.consumer.RedisClientService;
import com.ns.sso.utils.CookieUtils;
import com.ns.sso.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:31
 */
public class WebInterceptor implements HandlerInterceptor {

    @Autowired
    RedisClientService redisClientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isBlank(token)) {
            System.out.println(request.getRequestURL());
            response.sendRedirect("http://127.0.0.1/user/login?url=" + request.getRequestURL());
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (user == null) {
            String token = CookieUtils.getCookieValue(request, "token");
            if (StringUtils.isNotBlank(token)) {
                String s = redisClientService.get(token);
                if (StringUtils.isNotBlank(s)) {
                    String s1 = redisClientService.get(s);
                    if (StringUtils.isNotBlank(s1)) {
                        User user1 = JsonUtils.jsonToPojo(s1,User.class);
                        httpSession.setAttribute("user",user1);
                    }
                }
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
