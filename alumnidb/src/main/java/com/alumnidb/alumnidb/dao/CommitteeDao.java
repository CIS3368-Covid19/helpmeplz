package com.alumnidb.alumnidb.dao;

import com.alumnidb.alumnidb.entity.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitteeDao extends JpaRepository<Committee, Long> {

    @Query(value ="SELECT i FROM Committee as i WHERE i.firstName like %:name OR i.lastName like %:name")
    List<Committee> findCommitteeByName(String name);

    @Query(value = "SELECT i FROM Committee i WHERE i.user.email = :email")
    Committee findCommitteeByEmail(String email);
}
