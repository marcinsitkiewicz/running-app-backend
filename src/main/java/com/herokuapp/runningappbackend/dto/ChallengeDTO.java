package com.herokuapp.runningappbackend.dto;

import com.herokuapp.runningappbackend.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ChallengeDTO {

    private Long challengeId;
    private Set<User> participants;
    private String name;
    private String description;
    private Double amountToComplete;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
