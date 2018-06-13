package com.sun.demo.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public interface ResourceIgnoreConfig {

    @Bean
    default List<String> ResGETIgnoreConfig(){
        List<String> ignoreConfig = new ArrayList();
        return ignoreConfig;
    }


    @Bean
    default List<String> ResIgnoreConfig(){
        List<String> ignoreConfig = new ArrayList();
        return ignoreConfig;
    }

}
