package com.sun.demo.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Wayne on 2018/06/08.
 */
@ComponentScan(basePackages = {"com.sun.**.service", "com.sun.**.controller", "com.sun.**.oauth2", "com.sun.demo.config"})
@MapperScan("com.sun.**.dao")
@EnableAutoConfiguration
@EnableConfigurationProperties
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

