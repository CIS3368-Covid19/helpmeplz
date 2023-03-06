package com.alumnidb.alumnidb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "duration", nullable = false)
    private String duration;

    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "event")
    private Set<Alumni> alumni = new HashSet<>();

    @ManyToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<Committee> committees = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    public Event(String name, String duration, String description, User organizer) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.organizer = organizer;
    }
    public void removeAlumniFromEvent(Alumni alumni) {
        this.alumni.remove(alumni);
        alumni.getEvents().remove(this);
    }
    public Set<User> getUsers() {
        return users;
    }
}

