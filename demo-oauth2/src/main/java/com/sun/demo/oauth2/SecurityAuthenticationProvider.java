package com.sun.demo.oauth2;

import com.sun.demo.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {
    private Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Resource
//    SystemUserDao systemUserDao;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = null;
        try {
            List<GrantedAuthority> authority = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            UserDetails userDetails = new OAuth2UserDetails(user, authority);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            return token;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    public boolean supports(Class<?> authentication) {
        return true;
    }

//    private User getUser(String username) {
//        User sysUser = new User();
//        sysUser.setUsername(username);
//        return systemUserDao.selectOne(sysUser);
//    }
}

