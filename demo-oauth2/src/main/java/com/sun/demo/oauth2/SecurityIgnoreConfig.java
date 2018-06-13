package com.sun.demo.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public interface SecurityIgnoreConfig {
    @Bean
    default List<String> SecGETIgnoreConfig(){
        List<String> ignoreConfig = new ArrayList();
        return ignoreConfig;
    }


    @Bean
    default List<String> SecIgnoreConfig() {
        List<String> ignoreConfig = new ArrayList();
        return ignoreConfig;
    }
}
