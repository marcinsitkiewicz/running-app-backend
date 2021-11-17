package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_challenge")
public class UserChallenge {

    @EmbeddedId
    private UserChallengeId id;

    @ManyToOne
    @MapsId(value = "userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId(value = "challengeId")
    @JoinColumn(name = "challenge_id")
    @JsonIgnore
    private Challenge challenge;

    @Column(name = "current_amount")
    private Double currentAmount;

    public UserChallenge(User user, Challenge challenge, Double currentAmount) {
        this.id = new UserChallengeId(user.getId(), challenge.getId());
        this.user = user;
        this.challenge = challenge;
        this.currentAmount = currentAmount;
    }
}