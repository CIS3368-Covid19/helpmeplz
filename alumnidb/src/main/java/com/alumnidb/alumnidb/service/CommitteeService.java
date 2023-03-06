package com.alumnidb.alumnidb.service;

import com.alumnidb.alumnidb.entity.Committee;

import java.util.List;

public interface CommitteeService {

  Committee loadCommitteeById(Long committeeId);

  List<Committee> findCommitteeByName(String name);

  Committee loadCommitteeByEmail(String email);

  Committee createCommittee(String firstName, String lastName, String summary, String email, String password);

  Committee updateCommittee(Committee committee);

  List<Committee> fetchCommittee();

  void removeCommittee(Long committeeId);
}
