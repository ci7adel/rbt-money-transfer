package com.victorbarca.rbtapp.controllers;

import com.victorbarca.rbtapp.data.User;
import com.victorbarca.rbtapp.services.UserService;

import java.util.Collection;

public class UserController {

    private UserService userService = new UserService();

    public User getUser(Integer userId) {
        return userService.getUser(userId);
    }

    public void createUser(User user) throws Exception {
        if(!userService.userExists(user.getUserId())) {
            userService.createUser(user);
        }else throw new Exception("User ID already exists");
    }

    public void deleteUser(Integer userId) throws Exception {
        if (userService.userExists(userId) && !userService.hasAccounts(userId))
            userService.deleteUser(userId);
        else throw new Exception("User not found or with accounts");
    }

    public void updateUser(User user) throws Exception {
        if (userService.userExists(user.getUserId()))
            this.userService.updateUser(user);
        else throw new Exception("User not found");
    }

    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    public boolean userExists(Integer userId) {
        return userService.userExists(userId);
    }


}
