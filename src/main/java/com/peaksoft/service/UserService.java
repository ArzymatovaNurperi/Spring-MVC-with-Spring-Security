package com.peaksoft.service;

import com.peaksoft.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user); //String id);

    void deleteUser(User user);

    User get(long id);

    User findByUsername(String username);
}
