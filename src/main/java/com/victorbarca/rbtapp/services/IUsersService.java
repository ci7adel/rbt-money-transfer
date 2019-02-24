package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.User;

public interface IUsersService {
    public User getUser(String userId);

    public void createUser(User user);

    public void deleteUser(String userId);

    public void updateUser(User user);
}
