package com.alumnidb.alumnidb.dao;

import com.alumnidb.alumnidb.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {
    List<Event> findByNameContaining(String name);
}

