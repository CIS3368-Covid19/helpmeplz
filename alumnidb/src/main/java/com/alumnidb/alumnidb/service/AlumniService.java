package com.alumnidb.alumnidb.service;

import com.alumnidb.alumnidb.entity.Alumni;

import java.util.List;

public interface AlumniService {

    Alumni loadAlumniById(Long alumniId);

    List<Alumni> findAlumniByName(String name);

    Alumni loadAlumniByEmail(String email);

    Alumni createAlumni(String firstName, String lastName, String summary, String email, String password);

    Alumni updateAlumni(Alumni alumni);

    List<Alumni> fetchAlumni();

    void removeAlumni(Long alumniId);
}
