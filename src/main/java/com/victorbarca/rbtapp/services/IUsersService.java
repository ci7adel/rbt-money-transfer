package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.User;

import java.util.Collection;

public interface IUsersService {
    User getUser(Integer userId);

    void createUser(User user) throws Exception;

    void deleteUser(Integer userId);

    void updateUser(User user);

    Collection<User> getUsers();

    boolean hasAccounts(Integer userId);
}
