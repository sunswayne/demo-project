package com.sun.demo.controller.swagger2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * Swagger注入及参数配置
 */
@EnableSwagger2
@Configuration
@Profile(value={"test","dev"})
@PropertySource(ignoreResourceNotFound = true, value = {"classpath:api.properties"}, encoding = "UTF-8")
public class Swagger2Configuration {
    @Value("${springfox.documentation.swagger.v2.title}")
    String title;
    @Value("${springfox.documentation.swagger.v2.description}")
    String description;
    @Value("${springfox.documentation.swagger.v2.url}")
    String url;
    @Value("${springfox.documentation.swagger.v2.version}")
    String version;
    @Value("${springfox.documentation.swagger.v2.contact.name}")
    String contact_name;
    @Value("${springfox.documentation.swagger.v2.contact.email}")
    String contact_email;
    @Value("${springfox.documentation.swagger.v2.contact.url}")
    String contact_url;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sun.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(url)
                //.contact(new Contact(contact_name,contact_url,contact_email))
                .version(version)
                .build();
    }
}

