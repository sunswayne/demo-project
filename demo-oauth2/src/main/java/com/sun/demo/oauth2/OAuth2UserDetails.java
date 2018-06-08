package com.sun.demo.oauth2;

import com.sun.demo.bean.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class OAuth2UserDetails extends User implements UserDetails {

    private List<GrantedAuthority> roles;
    private User user;

    public OAuth2UserDetails(User user, List<GrantedAuthority> roles) {
        this.roles = roles;
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public List<GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(List<GrantedAuthority> roles) {
        this.roles = roles;
    }
}
