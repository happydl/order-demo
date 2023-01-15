package com.ldm.order.interceptor;

import com.ldm.order.utils.CacheUtils;
import com.ldm.order.common.RespCode;
import com.ldm.order.exception.MyException;
import com.ldm.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ValidationInterceptor implements HandlerInterceptor {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getServletPath();

        if (!isEscapePath(path)) {
            checkToken(request);
        }
        return true;
    }

    private void checkToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "token");
        if (cookie != null) {
            System.out.printf("interceptor: token %s %n", cookie.getValue());
            if (userService.checkToken(cookie.getValue())) {
                CacheUtils.setCurrentToken(cookie.getValue());
            } else {
                throw new MyException(RespCode.FAIL, "用户未登录");
            }
        } else {
            throw new MyException(RespCode.FAIL, "用户未登录");
        }
    }

    private boolean isEscapePath(String path) {
//        return true;
        return path.contains("login") || path.contains("register");
    }
}
