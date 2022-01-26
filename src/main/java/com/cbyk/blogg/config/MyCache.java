package com.cbyk.blogg.config;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class MyCache {
    public static ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<>();


    public static Object fetch(String key) {
        return chm.get(key);
    }

    public static void store(String key, Object val) {
        chm.put(key, val);
    }

    public static void evict(String key) {
        chm.remove(key);
    }

    public static void evict() {
        chm.clear();
    }

}
