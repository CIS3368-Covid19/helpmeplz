package com.alumnidb.alumnidb.impl;

import com.alumnidb.alumnidb.service.UserService;
import com.alumnidb.alumnidb.dao.RoleDao;
import com.alumnidb.alumnidb.dao.UserDao;
import com.alumnidb.alumnidb.entity.Role;
import com.alumnidb.alumnidb.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        return userDao.save(new User(email, password));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user =loadUserByEmail(email);
        Role role = roleDao.findByName(roleName);
        user.assignRoleToUser(role);
    }
}
