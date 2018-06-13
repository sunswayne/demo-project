package com.sun.demo.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {
    private Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

    @Autowired
    private SecurityUserDetailsService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = null;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }

        try {
            Collection<? extends GrantedAuthority> authorities = ((OAuth2UserDetails)userDetails).getAuthorities();
            return new UsernamePasswordAuthenticationToken(((OAuth2UserDetails)userDetails), password, authorities);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    public boolean supports(Class<?> authentication) {
        return true;
    }

}

