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
    public Set<UserChallenge> userChallenges = new HashSet<>();

    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String email;

    private String firstName;

    private String lastName;

    private Date birthDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Activity> activities;

    @OneToMany(mappedBy = "postAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;

    @ManyToMany
    private Set<Post> likedPosts;

    public User(String password, String email, String firstName, String lastName, Date birthDate) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void addLike(Post post) {
        this.likedPosts.add(post);
        post.getLikes().add(this);
    }

    public void removeLike(Post post) {
        this.likedPosts.remove(post);
        post.getLikes().remove(this);
    }
}
