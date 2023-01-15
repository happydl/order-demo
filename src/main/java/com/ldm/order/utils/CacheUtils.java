package com.ldm.order.utils;


import com.ldm.order.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CacheUtils {

    static private ThreadLocal<String> currentToken = new ThreadLocal<>();

    static public String getCurrentToken() {
        return currentToken.get();
    }

    static public void setCurrentToken(String _currentToken) {
        currentToken.set(_currentToken);
    }

}
