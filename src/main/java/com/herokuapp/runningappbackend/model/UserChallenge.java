package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_challenge")
public class UserChallenge {

//    @EmbeddedId
//    private UserChallengeId id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId(value = "userId")
//    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId(value = "challengeId")
//    @JoinColumn(name = "challenge_id")
    @JsonIgnore
    private Challenge challenge;

    @Column(name = "current_amount")
    private Double currentAmount;

    @Column(name = "join_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "Europe/Warsaw")
    private LocalDateTime joinDate;

    @Column(name = "complete_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "Europe/Warsaw")
    private LocalDateTime completeDate;

    private Boolean isCompleted;

    public UserChallenge(User user, Challenge challenge, Double currentAmount, LocalDateTime joinDate) {
//        this.id = new UserChallengeId(user.getId(), challenge.getId());
        this.user = user;
        this.challenge = challenge;
        this.currentAmount = currentAmount;
        this.joinDate = joinDate;
        this.isCompleted = false;
    }

    public void deleteUser() {
        this.user.deleteUserChallenge(this);
        this.user = null;
    }

    public void deleteChallenge() {
        this.challenge.deleteUserChallenge(this);
        this.challenge = null;
    }

    public void deleteId() {
        this.id = null;
    }
}
