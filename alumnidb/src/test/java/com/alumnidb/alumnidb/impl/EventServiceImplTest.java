package com.alumnidb.alumnidb.impl;
import com.alumnidb.alumnidb.entity.User;
import com.alumnidb.alumnidb.impl.EventServiceImpl;
import com.alumnidb.alumnidb.dao.CommitteeDao;
import com.alumnidb.alumnidb.dao.EventDao;
import com.alumnidb.alumnidb.entity.Committee;
import com.alumnidb.alumnidb.entity.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {


    @Mock
    private EventDao eventDao;

    @InjectMocks
    private EventServiceImpl eventService;
    @Test
    void testLoadEventById() {
        // Create a new Event object with ID 1
        Event event = new Event();
        event.setEventId(1L);

        // Mock the findById method of EventDao to return the event object created
        when(eventDao.findById(any())).thenReturn(Optional.of(event));

        // LoadEventById method of the EventServiceImpl with ID 1 and storing
        Event actualEvent = eventService.loadEventById(1L);

        // Checks expected and actual Event objects = same
        assertEquals(event, actualEvent);
    }

    @Test
    void loadEventById_shouldRuntimeExceptionIfEventNotFound() {
        long eventId = 2L;

        // Mocks the findById method of the EventDao to return an empty Optional object
        when(eventDao.findById(eventId)).thenReturn(Optional.empty());

        // loadEventById method of the EventServiceImpl with the event ID and assert that it throws a RuntimeException
        assertThrows(RuntimeException.class, () -> eventService.loadEventById(eventId), "Event with id " + eventId + " not found");
    }


    @Test
    void testCreateEventSuccess() {
        // Create test data
        String name = "Test Event";
        String duration = "1 hour";
        String description = "This is a test event";
        User organizer = new User();
        List<Committee> committees = new ArrayList<>();

        // Mock the EventDao and its save method
        Event savedEvent = new Event(name, duration, description, organizer);
        when(eventDao.save(any(Event.class))).thenReturn(savedEvent);

        // Call the createEvent method and store the result
        Event createdEvent = eventService.createEvent(name, duration, description, organizer, committees);

        // Verify that the save method of the EventDao was called once with the expected Event object
        verify(eventDao, times(1)).save(any(Event.class));

        // Check that the returned Event object is not null
        assertNotNull(createdEvent);

        // Check that the saved Event object has the expected values
        assertEquals(name, createdEvent.getName());
        assertEquals(duration, createdEvent.getDuration());
        assertEquals(description, createdEvent.getDescription());
        assertEquals(organizer, createdEvent.getOrganizer());
        assertEquals(committees, createdEvent.getCommittees());
    }

    @Test
    void testCreateEventNullParameter() {
        // Check that a NullPointerException is thrown if any of the required parameters are null
        assertThrows(NullPointerException.class, () -> {
            eventService.createEvent(null, "1 hour", "This is a test event", new User(), new ArrayList<>());
        });
    }

    @Test
    void getEventsByKeyword() {
    }

    @Test
    void updateEvent() {
    }

    @Test
    void getAllEvents() {
    }

    @Test
    void findEventsByNameContaining() {
    }

    @Test
    void deleteEventById() {
    }

    @Test
    void fetchEventForAlumni() {
    }

    @Test
    void assignAlumniToEvent() {
    }

    @Test
    void testCreateEvent() {
    }

    @Test
    void createOrUpdateEvent() {
    }
}