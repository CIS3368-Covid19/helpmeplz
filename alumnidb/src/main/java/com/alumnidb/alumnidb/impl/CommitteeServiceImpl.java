package com.alumnidb.alumnidb.impl;

import com.alumnidb.alumnidb.dao.CommitteeDao;
import com.alumnidb.alumnidb.entity.Committee;
import com.alumnidb.alumnidb.entity.Event;
import com.alumnidb.alumnidb.entity.User;
import com.alumnidb.alumnidb.service.CommitteeService;
import com.alumnidb.alumnidb.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CommitteeServiceImpl implements CommitteeService {

    private CommitteeDao committeeDao;
    private UserService userService;

    public CommitteeServiceImpl(CommitteeDao committeeDao, UserService userService) {
        this.committeeDao = committeeDao;
        this.userService = userService;
    }

    @Override
    public Committee loadCommitteeById(Long committeeId) {
        return committeeDao.findById(committeeId)
                .orElseThrow(() -> new EntityNotFoundException("Committee with id " + committeeId + " not found"));
    }

    @Override
    public List<Committee> findCommitteeByName(String name) {
        return committeeDao.findCommitteeByName(name);
    }

    @Override
    public Committee loadCommitteeByEmail(String email) {
        return committeeDao.findCommitteeByEmail(email);
    }

    @Override
    public Committee createCommittee(String firstName, String lastName, String summary, String email, String password) {
        User user = userService.createUser(email, password);
        userService.assignRoleToUser(email, "Committee");
        Committee committee = new Committee(firstName, lastName, summary, user);
        return committeeDao.save(committee);
    }

    @Override
    public Committee updateCommittee(Committee committee) {
        return committeeDao.save(committee);
    }

    @Override
    public List<Committee> fetchCommittee() {
        return committeeDao.findAll();
    }

    @Override
    public void removeCommittee(Long committeeId) {
        Committee committee = loadCommitteeById(committeeId);
        for (Event event : committee.getEvents()) {
            event.getCommittees().remove(committee);
        }
        committeeDao.deleteById(committeeId);
    }
}
