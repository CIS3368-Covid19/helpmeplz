package com.alumnidb.alumnidb.impl;
import com.alumnidb.alumnidb.dao.EventDao;
import com.alumnidb.alumnidb.entity.Committee;
import com.alumnidb.alumnidb.entity.Event;
import com.alumnidb.alumnidb.entity.User;
import com.alumnidb.alumnidb.service.EventService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventDao eventDao;

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event loadEventById(Long eventId) {
        return eventDao.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + eventId + " Not Found"));
    }

    @Override
    public List<Event> findEventByName(String name) {
        return eventDao.findByNameContaining(name);
    }

    @Override
    public Event createEvent(String name, String duration, String description, User organizer, List<Committee> committees) {
        Event event = new Event(name, duration, description, organizer);
        event.getCommittees().addAll(committees);
        return eventDao.save(event);
    }


    @Override
    public Event updateEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventDao.deleteById(eventId);
    }

    @Override
    public List<Event> fetchEvents() {
        return eventDao.findAll();
    }
}
