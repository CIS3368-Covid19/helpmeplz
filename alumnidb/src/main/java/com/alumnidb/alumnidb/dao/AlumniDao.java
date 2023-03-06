package com.alumnidb.alumnidb.dao;

import com.alumnidb.alumnidb.entity.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlumniDao extends JpaRepository<Alumni, Long>  {

    @Query(value ="SELECT i FROM Alumni as i WHERE i.firstName like %:name OR i.lastName like %:name")
    List<Alumni> findAlumniByName(@Param("name") String name);

    @Query(value = "SELECT i FROM Alumni i WHERE i.user.email = :email")
    Alumni findAlumniByEmail(String email);
}
