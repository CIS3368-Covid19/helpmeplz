package com.alumnidb.alumnidb.service;


import com.alumnidb.alumnidb.entity.User;

public interface UserService {

    User loadUserByEmail(String email);

    User createUser(String email, String password);

    void assignRoleToUser(String email, String roleName);
}
