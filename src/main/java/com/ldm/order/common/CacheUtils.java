package com.ldm.order.common;


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
