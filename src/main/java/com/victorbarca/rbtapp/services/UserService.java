package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.User;

import java.util.HashMap;

public class UserService implements IUsersService{
    private HashMap<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    @Override
    public User getUser(String userId) {
        return users.containsKey(userId) ? users.get(userId) : new User("U00001","Victor");
    }

    @Override
    public void createUser(User user) {
        this.users.put(user.userId, user);
    }

    @Override
    public void deleteUser(String userId) {}

    @Override
    public void updateUser(User user) {}
}
