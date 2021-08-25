package com.peaksoft.dao;

import com.peaksoft.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User get(long id);

    User findByUsername(String username);


}
