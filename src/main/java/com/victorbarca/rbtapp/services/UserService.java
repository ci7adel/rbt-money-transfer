package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.User;

import java.util.HashMap;

public class UserService implements IUsersService{
    private HashMap<Integer, User> users;

    public UserService() {
        this.users = new HashMap<>();
        this.users.put(1,new User(1,"Victor"));
    }

    @Override
    public User getUser(String userId) {
        return users.getOrDefault(userId, null);
    }

    @Override
    public void createUser(User user) throws Exception {
        if(!this.users.containsKey(user.getUserId())) {
            this.users.put(user.getUserId(), user);
        }else throw new Exception("User id already exists");
    }

    @Override
    public void deleteUser(String userId) {}

    @Override
    public void updateUser(User user) {}
}
