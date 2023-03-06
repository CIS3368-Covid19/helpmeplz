package com.alumnidb.alumnidb.dao;

import com.alumnidb.alumnidb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
