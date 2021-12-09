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

    @ManyToOne
    private User user;

    @OneToOne
    private Post postActivity;

    @ManyToOne
    private UserChallenge userChallenge;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Image activityImage;

    //TODO: total time (minutes), calories, distance, avarage pace, avarage speed, bitmap

    public Activity(User user, Post postActivity, UserChallenge userChallenge, LocalDateTime startDate, LocalDateTime endDate) {
        this.user = user;
        this.postActivity = postActivity;
        this.userChallenge = userChallenge;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
