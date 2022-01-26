package com.cbyk.blogg.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResourceService {


    public ClassPathResource fetchResource(String name, String type) {
        String appender = "";
        ClassPathResource classPathResource = null;
        if (type.equals("img"))
            appender = "static/assets/img/";

        StringBuilder sb = new StringBuilder().append(appender).append(name);

        try {
            classPathResource = new ClassPathResource(sb.toString());
        } catch (Exception e) {
            System.out.println("ClassPathResource not found at path:");
            return null;
        }
        return classPathResource;

    }

}
