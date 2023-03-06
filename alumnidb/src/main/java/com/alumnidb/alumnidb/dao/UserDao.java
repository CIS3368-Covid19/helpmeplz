package com.alumnidb.alumnidb.dao;

import com.alumnidb.alumnidb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

