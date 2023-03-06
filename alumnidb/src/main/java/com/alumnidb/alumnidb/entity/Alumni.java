package com.alumnidb.alumnidb.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "alumni")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Alumni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alumni_id", nullable = false)
    private Long alumniId;

    @NonNull
    @Column(name = "batch", nullable = false)
    private String batch;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Basic
    @Column(name = "graduation_year", nullable = false)
    private int graduationYear;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "alumni_event",
            joinColumns = @JoinColumn(name = "alumni_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events = new HashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public Alumni(String firstName, String lastName, int graduationYear, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.graduationYear = graduationYear;
        this.user = user;
    }

    public Long getId() {
        return this.alumniId;
    }

    public void setId(Long alumniId) {
        this.alumniId = alumniId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Set<Event> getAttendedEvents() {
        return events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumni alumni = (Alumni) o;
        return graduationYear == alumni.graduationYear && Objects.equals(alumniId, alumni.alumniId) && Objects.equals(firstName, alumni.firstName) && Objects.equals(lastName, alumni.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumniId, firstName, lastName, graduationYear);
    }

    @Override
    public String toString() {
        return "Alumni{" +
                "alumniId=" + alumniId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", graduationYear=" + graduationYear +
                '}';
    }
}
