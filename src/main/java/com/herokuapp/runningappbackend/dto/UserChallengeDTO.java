package com.herokuapp.runningappbackend.dto;

import com.herokuapp.runningappbackend.model.Challenge;
import com.herokuapp.runningappbackend.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserChallengeDTO {

    private UserChallengeIdDTO id;
    private User user;
    private Challenge challenge;
    private Double currentAmount;
    private LocalDateTime joinDate;
    private LocalDateTime completeDate;
}
