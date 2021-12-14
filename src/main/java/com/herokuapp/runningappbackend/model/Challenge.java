package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "\"challenge\"")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserChallenge> userChallenges = new HashSet<>();

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    private Double amountToComplete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
                timezone = "Europe/Warsaw")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
                timezone = "Europe/Warsaw")
    private LocalDateTime endDate;

    private Long participantsAmount;

    public Challenge(String name, String description, Double amountToComplete, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.description = description;
        this.amountToComplete = amountToComplete;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participantsAmount = 0L;
    }

    public void deleteUserChallenge(UserChallenge userChallenge) {
        this.userChallenges.remove(userChallenge);
    }

    public void deleteUserChallenges() {
        this.userChallenges.forEach(UserChallenge::deleteChallenge);
        this.userChallenges.clear();
    }
}
