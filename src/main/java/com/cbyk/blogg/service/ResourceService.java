package com.cbyk.blogg.service;

import com.cbyk.blogg.config.MyCache;
import com.cbyk.blogg.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResourceService {

    public static String IMG_PATH_PREFIX = "static/assets/img/";

    @Cacheable(value="resources")
    public ClassPathResource fetchResource(String name, String type) {
        String appender = "";
        ClassPathResource classPathResource = null;
        if (type.equals(AppConstants.IMAGE_STRING))
            appender = IMG_PATH_PREFIX;

        StringBuilder fullPathSB = new StringBuilder().append(appender).append(name);

        try {
            classPathResource = new ClassPathResource(fullPathSB.toString());
        } catch (Exception e) {
            System.out.println("ClassPathResource not found at path:");
            return null;
        }
        return classPathResource;

    }

}
