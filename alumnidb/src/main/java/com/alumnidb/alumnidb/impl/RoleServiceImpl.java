package com.alumnidb.alumnidb.impl;

import com.alumnidb.alumnidb.service.RoleService;
import com.alumnidb.alumnidb.dao.RoleDao;
import com.alumnidb.alumnidb.entity.Role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role loadRoleByName(String roleName) {
        return roleDao.findByName(roleName);
    }

    @Override
    public Role createRole(String roleName) {
        return roleDao.save(new Role(roleName));
    }
}
