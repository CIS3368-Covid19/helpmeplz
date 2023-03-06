package com.alumnidb.alumnidb.impl;

import com.alumnidb.alumnidb.service.AlumniService;
import com.alumnidb.alumnidb.service.UserService;
import com.alumnidb.alumnidb.dao.AlumniDao;
import com.alumnidb.alumnidb.entity.Alumni;
import com.alumnidb.alumnidb.entity.Event;
import com.alumnidb.alumnidb.entity.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class AlumniServiceImpl implements AlumniService {

    private AlumniDao alumniDao;

    private UserService userService;

    public AlumniServiceImpl(AlumniDao alumniDao, UserService userService) {
        this.alumniDao = alumniDao;
        this.userService = userService;
    }

    @Override
    public Alumni loadAlumniById(Long alumniId) {
        return alumniDao.findById(alumniId).orElseThrow(() -> new EntityNotFoundException("Alumni with id " + alumniId + " Not Found"));
    }

    @Override
    public List<Alumni> findAlumniByName(String name) {
        return alumniDao.findAlumniByName(name);
    }

    @Override
    public Alumni loadAlumniByEmail(String email) {
        return alumniDao.findAlumniByEmail(email);
    }

    @Override
    public Alumni createAlumni(String firstName, String lastName, String batch, String email, String password) {
        User user = userService.createUser(email, password);
        userService.assignRoleToUser(email, "Alumni");
        int batchYear = Integer.parseInt(batch);
        return alumniDao.save(new Alumni(firstName, lastName, batchYear, user));
    }

    @Override
    public Alumni updateAlumni(Alumni alumni) {
        Alumni existingAlumni = loadAlumniById(alumni.getAlumniId());
        existingAlumni.setFirstName(alumni.getFirstName());
        existingAlumni.setLastName(alumni.getLastName());
        existingAlumni.setBatch(alumni.getBatch());
        existingAlumni.getUser().setPassword(alumni.getUser().getPassword());
        return alumniDao.save(existingAlumni);
    }


    @Override
    public List<Alumni> fetchAlumni() {
        return alumniDao.findAll();
    }

    @Override
    public void removeAlumni(Long alumniId) {
        Alumni alumni = loadAlumniById(alumniId);
        Iterator<Event> iterator = alumni.getEvents().iterator();
        if(iterator.hasNext()) {
            Event event = iterator.next();
            event.removeAlumniFromEvent(alumni);
        }
        alumniDao.deleteById(alumniId);
    }
}

