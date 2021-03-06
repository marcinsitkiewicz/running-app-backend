package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserChallengeDTO {

    private Long id;
    private UserDTO user;
    private ChallengeDTO challenge;
    private Double currentAmount;
    private LocalDateTime joinDate;
    private LocalDateTime completeDate;
    private Boolean isCompleted;
}
