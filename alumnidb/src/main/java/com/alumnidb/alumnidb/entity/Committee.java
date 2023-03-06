package com.alumnidb.alumnidb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "committee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Committee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "committee_id", nullable = false)
    private Long committeeId;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NonNull
    @Column(name = "summary", nullable = false)
    private String summary;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "committees")
    private Set<Event> events = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return committeeId.equals(committee.committeeId) &&
                firstName.equals(committee.firstName) &&
                lastName.equals(committee.lastName) &&
                summary.equals(committee.summary) &&
                user.equals(committee.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(committeeId, firstName, lastName, summary, user);
    }

    @Override
    public String toString() {
        return "Committee{" +
                "committeeId=" + committeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }

    public Committee(String firstName, String lastName, String summary, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
        this.user = user;
    }

    public Long getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(Long committeeId) {
        this.committeeId = committeeId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
