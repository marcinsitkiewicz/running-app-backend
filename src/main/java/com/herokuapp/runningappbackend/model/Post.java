package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "\"post\"")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User postAuthor;

    @Column(name = "posted_date")
    private LocalDateTime postedDate;

//    @Column(name = "likes")
//    @ManyToMany(mappedBy = "likedPosts")
//    private Set<User> likes = new HashSet<>();

//    @OneToOne(mappedBy = "postActivity")
//    private Activity activity;

    public Post(User postAuthor, LocalDateTime postedDate, Activity activity) {
//        this.postAuthor = postAuthor;
        this.postedDate = postedDate;
    }
}
