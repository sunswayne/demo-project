package com.sun.demo.config;

import com.sun.demo.oauth2.ResourceIgnoreConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(1)
public class FrameworkResourceIgnoreConfig implements ResourceIgnoreConfig {
    @Bean
    public List<String> ResGETIgnoreConfig() {
        List<String> ignoreConfig = new ArrayList();
        ignoreConfig.add("/user/get-user-by-id");
        return ignoreConfig;
    }

    @Bean
    public List<String> ResIgnoreConfig() {
        List<String> ignoreConfig = new ArrayList();
        ignoreConfig.add("/user/get-user-by-id");
        return ignoreConfig;
    }

}
