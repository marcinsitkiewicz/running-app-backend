package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "\"post\"")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @Column(name = "post_author")
    private User postAuthor;

    @Column(name = "posted_date")
    private LocalDateTime postedDate;

    @Column(name = "likes")
    @ManyToMany(mappedBy = "likedPosts")
    private Set<User> likes = new HashSet<>();

    @OneToOne(mappedBy = "activity")
    private Activity activity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Image postImage;

    public Post(User postAuthor, LocalDateTime postedDate) {
        this.postAuthor = postAuthor;
        this.postedDate = postedDate;
    }
}
