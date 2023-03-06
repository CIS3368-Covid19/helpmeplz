package com.alumnidb.alumnidb.service;

import com.alumnidb.alumnidb.entity.Committee;
import com.alumnidb.alumnidb.entity.Event;
import com.alumnidb.alumnidb.entity.User;

import java.util.List;

public interface EventService {
    Event loadEventById(Long eventId);

    List<Event> findEventByName(String name);

    Event createEvent(String name, String description, String location, User organizer, List<Committee> committees);

    Event updateEvent(Event event);

    void deleteEventById(Long eventId);

    List<Event> fetchEvents();
}




