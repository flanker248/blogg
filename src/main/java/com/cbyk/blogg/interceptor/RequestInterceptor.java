package com.cbyk.blogg.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    public static HashMap<String,Integer> reqIp=new HashMap<>();

    public static<K> void incrementValue(Map<String, Integer> map, String key)
    {
        Integer count = map.get(key);
        if (count == null) {
            count = 0;
        }
        map.put(key, count + 1);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        if(request.getRequestURI().contains("/notes/") ||
                request.getRequestURI().contains("/blog/")){
            incrementValue(reqIp,ipAddress);
        }
        return true;
    }
}