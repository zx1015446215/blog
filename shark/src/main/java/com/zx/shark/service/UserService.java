package com.zx.shark.service;


import com.zx.shark.model.Role;
import com.zx.shark.model.User;
import com.zx.shark.model.User_Roles;

public interface UserService {
    public void registerUser(User user);
    public User findUserByUsername(String username);
    public void saveRole(Role role);
    public Role findRoleById(long id);
    public void saveUser_Roles(User_Roles user_roles);
}
