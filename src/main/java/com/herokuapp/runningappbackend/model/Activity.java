package com.herokuapp.runningappbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "\"activity\"")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

//    @OneToOne
//    @JsonIgnore
//    private Post postActivity;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Image mapImage;

    private String totalTime;

    private int calories;

    private int distance;

    private String pace;

    private Float speed;

    private Boolean isPosted;

    private LocalDateTime datePosted;

//    @Column(name = "likes")
//    @ManyToMany(mappedBy = "likedActivities")
//    private Set<User> likes = new HashSet<>();

    private int likesAmount;

    public Activity(User user,
                    LocalDateTime date,
                    Image mapImage,
                    String totalTime,
                    int calories,
                    int distance,
                    String pace,
                    Float speed,
                    Boolean isPosted) {
        this.user = user;
        this.date = date;
        this.mapImage = mapImage;
        this.totalTime = totalTime;
        this.calories = calories;
        this.distance = distance;
        this.pace = pace;
        this.speed = speed;
        this.isPosted = isPosted;
        this.likesAmount = 0;
        this.datePosted = LocalDateTime.now();
    }

//    public int getLikesAmount() {
//        return likes.size();
//    }
//
//    public void unlike(User user) {
//        this.likes.remove(user);
//        user.getLikedActivities().remove(this);
//    }
}
