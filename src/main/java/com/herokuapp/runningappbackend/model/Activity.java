package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @OneToOne
    private Post postActivity;

    @ManyToOne
    private UserChallenge userChallenge;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Image mapImage;

    private String totalTime;

    private int calories;

    private int distance;

    private String pace;

    private Float speed;

    public Activity(User user,
//                    UserChallenge userChallenge,
                    LocalDateTime date,
                    Image mapImage,
                    String totalTime,
                    int calories,
                    int distance,
                    String pace,
                    Float speed) {
        this.user = user;
//        this.userChallenge = userChallenge;
        this.date = date;
        this.mapImage = mapImage;
        this.totalTime = totalTime;
        this.calories = calories;
        this.distance = distance;
        this.pace = pace;
        this.speed = speed;
    }
}
