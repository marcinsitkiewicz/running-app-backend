package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public Set<UserChallenge> userChallenges = new HashSet<>();

    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private Date birthDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Activity> activities;

    @OneToMany(mappedBy = "postAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Post> posts;

    @ManyToMany
    @JsonIgnore
    private Set<Activity> likedActivities;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Image userImage;

    public User(String password, String email, String firstName, String lastName, Date birthDate) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void addLike(Activity activity) {
        this.likedActivities.add(activity);
        activity.getLikes().add(this);
    }

    public void removeLike(Activity activity) {
        this.likedActivities.remove(activity);
        activity.getLikes().remove(this);
    }
}
