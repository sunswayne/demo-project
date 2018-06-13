package com.sun.demo.controller.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile(value={"test","dev"})
public interface SwaggerScanConfig {

    @Bean
    default List<String> ScanPackageName() {
        List<String> scanPackageName = new ArrayList<>();
        scanPackageName.add("com.sun.demo");
        return scanPackageName;
    }
}
