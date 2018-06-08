package com.sun.demo.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityAuthenticationProvider databaseAuthenticationProvider;

    @Autowired
    private List<String> SecGETIgnoreConfig;

    @Autowired
    private List<String> SecIgnoreConfig;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(databaseAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic().and()
                .csrf().disable();
        http.headers().frameOptions().disable();
    }

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        SecIgnoreConfig.add("/webjars/**");
        SecIgnoreConfig.add("swagger-resources/**");
        String[] _secIgnoreConfig = SecIgnoreConfig.toArray(new String[SecIgnoreConfig.size()]);
        String[] _secGETIgnoreConfig = SecGETIgnoreConfig.toArray(new String[SecGETIgnoreConfig.size()]);
        if (_secIgnoreConfig != null && _secIgnoreConfig.length > 0) {
            web.ignoring().antMatchers(_secIgnoreConfig);
        }
        if (_secGETIgnoreConfig != null && _secGETIgnoreConfig.length > 0) {
            web.ignoring().antMatchers(HttpMethod.GET, _secGETIgnoreConfig);
        }
    }

}