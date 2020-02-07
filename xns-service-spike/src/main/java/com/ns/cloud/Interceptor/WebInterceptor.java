package com.ns.cloud.Interceptor;

import com.ns.cloud.entities.User;
import com.ns.cloud.service.consumer.RedisClientService;
import com.ns.cloud.utils.CookieUtils;
import com.ns.cloud.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class WebInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisClientService redisClientService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isBlank(token)) {
            System.out.println(request.getRequestURL());
            response.sendRedirect("http://localhost:8001/user/login?url=" + request.getRequestURL());
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
                        httpSession.setAttribute("name",user1.getUserName());
                    }
                    else{
                        response.sendRedirect("http://localhost:8001/user/login?url=" + request.getRequestURL());
                    }
                }else{
                    response.sendRedirect("http://localhost:8001/user/login?url=" + request.getRequestURL());
                }
            }
            else{
                response.sendRedirect("http://localhost:8001/user/login?url=" + request.getRequestURL());
            }
        }else{
            response.sendRedirect("http://localhost:8001/user/login?url=" + request.getRequestURL());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
