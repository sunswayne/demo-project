package com.sun.demo.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import java.util.List;


@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${resource.id:order}")
    private String resourceId;

    @Autowired
    private List<String> ResGETIgnoreConfig;

    @Autowired
    private List<String> ResIgnoreConfig;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] _resIgnoreConfig = ResIgnoreConfig.toArray(new String[ResIgnoreConfig.size()]);
        String[] _resGETIgnoreConfig = ResGETIgnoreConfig.toArray(new String[ResGETIgnoreConfig.size()]);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();
        if (_resGETIgnoreConfig != null && _resGETIgnoreConfig.length > 0) {
            urlRegistry.antMatchers(HttpMethod.GET, _resGETIgnoreConfig).permitAll();
        }
        if (_resIgnoreConfig != null && _resIgnoreConfig.length > 0) {
            urlRegistry.antMatchers(_resIgnoreConfig).permitAll();
        }
        urlRegistry.antMatchers("/oauth2/**").permitAll();
        urlRegistry.antMatchers("/api/**", "/order/**", "/service/*", "/**/swagger-ui.html").authenticated().and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        http.headers().frameOptions().disable();
    }
}
