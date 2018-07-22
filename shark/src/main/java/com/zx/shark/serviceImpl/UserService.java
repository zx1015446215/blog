package com.zx.shark.serviceImpl;

import com.zx.shark.model.User;
import com.zx.shark.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserService implements UserDetailsService {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userServiceImpl.findUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("username is wrong");
        }
        return user;
    }
}
