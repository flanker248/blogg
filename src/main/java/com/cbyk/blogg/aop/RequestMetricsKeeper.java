package com.cbyk.blogg.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RequestMetricsKeeper {

    public static HashMap<String,Integer> reqIp=new HashMap<>();

    public static<K> void incrementValue(Map<String, Integer> map, String key)
    {
        Integer count = map.get(key);
        if (count == null) {
            count = 0;
        }
        map.put(key, count + 1);
    }


    //    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    @Pointcut("execution(* com.cbyk.blogg.service.BlogService.*(..))")
    public void BlogServicePointCut() {
    }

    @Around("BlogServicePointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
//        incrementValue(reqIp,"");
        System.out.println("************* at BlogServicePointCut");
        System.out.println("************* at BlogServicePointCut");
        return pjp.proceed();

    }

}
