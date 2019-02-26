package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.DataBase;
import com.victorbarca.rbtapp.data.User;

import java.util.Collection;

public class UserService implements IUsersService{
    private DataBase dataBase;

    public UserService() {
        this.dataBase = DataBase.getInstance();
    }

    @Override
    public User getUser(Integer userId) {
        return dataBase.users.getOrDefault(userId, null);
    }

    @Override
    public void createUser(User user) {
        dataBase.users.put(user.getUserId(), user);
    }

    @Override
    public void deleteUser(Integer userId) {
        dataBase.users.remove(userId);
    }

    @Override
    public void updateUser(User user) {
        dataBase.users.put(user.getUserId(), user);
    }

    public boolean userExists(Integer userId) {
        return dataBase.users.containsKey(userId);
    }

    @Override
    public Collection<User> getUsers() {
        return dataBase.users.values();
    }
}
