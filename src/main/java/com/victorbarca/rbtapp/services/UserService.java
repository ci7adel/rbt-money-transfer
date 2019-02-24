package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.User;

public class UserService implements IUsersService{

    @Override
    public User getUser(String userId) {
        return new User("U00001","Victor");
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void updateUser(User user) {

    }
}
