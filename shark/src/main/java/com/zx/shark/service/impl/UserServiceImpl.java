package com.zx.shark.service.impl;

import com.zx.shark.mapper.RoleMapper;
import com.zx.shark.mapper.UserMapper;
import com.zx.shark.model.Role;
import com.zx.shark.model.User;
import com.zx.shark.model.User_Roles;
import com.zx.shark.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void registerUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findUserByUsername(String username) {
//        ValueOperations<String,User> operations=redisTemplate.opsForValue();
//        boolean haskey= redisTemplate.hasKey(username);
//        //若缓存中存在
//        if(haskey){
//            User user=operations.get(username);
//            logger.info("从缓存中获取了用户: "+"id: "+ user.getId()+", username: "+user.getUsername()+",password: "+user.getPassword());
//            return user;
//        }
        User user=userMapper.selectByUsername(username);
//        //加入缓存
//        if(user!=null){
//            operations.set(username,user,1000, TimeUnit.SECONDS); //第三个参数设置过期时间
//            logger.info("用户插入缓存 >> " +"id: "+ user.getId()+", username: "+user.getUsername()+",password: "+user.getPassword());
//        }
        return user;
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public Role findRoleById(long id) {
        return roleMapper.selectByUserId(id);
    }

    @Override
    public void saveUser_Roles(User_Roles user_roles) {
        roleMapper.insertUser_Roles(user_roles);
    }
}
