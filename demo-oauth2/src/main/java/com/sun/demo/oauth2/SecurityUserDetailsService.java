package com.sun.demo.oauth2;

import com.sun.demo.bean.User;
import com.sun.demo.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Wayne on 2018-06-08.
 */
@Repository
public class SecurityUserDetailsService implements UserDetailsService {
    @Resource
    IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserService.getUserByUserName(username);
        if (user != null) {
            try {
//                List<SysRole> roles = iSysRoleService.getUserRole(user.getuId());
                return new OAuth2UserDetails(user, null);
            } catch (Exception e) {
                throw new UsernameNotFoundException("查询用户[" + username + "]角色失败");
            }
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }
    }
}
