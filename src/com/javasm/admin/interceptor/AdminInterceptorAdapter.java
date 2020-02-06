package com.javasm.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登陆拦截器
 * @Author 陈嘉浩
 * @Date 2020/2/5
 * @Version 1.0
 **/
public class AdminInterceptorAdapter implements HandlerInterceptor {

    @Override
    /*
     * @Description //登陆前拦截
     * @Param [request, response, handler]
     * @return boolean
     **/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        Object login_user = httpSession.getAttribute("LOGIN_USER");
        if(login_user!=null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/loginout");
            return false;
        }
    }
}
