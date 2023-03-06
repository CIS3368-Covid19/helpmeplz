package com.alumnidb.alumnidb.service;
import com.alumnidb.alumnidb.entity.Role;

public interface RoleService {

    Role loadRoleByName(String roleName);

    Role createRole(String roleName);
}


