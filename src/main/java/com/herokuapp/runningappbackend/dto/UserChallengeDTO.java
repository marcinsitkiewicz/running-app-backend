package com.herokuapp.runningappbackend.dto;

import com.herokuapp.runningappbackend.model.Challenge;
import com.herokuapp.runningappbackend.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChallengeDTO {

    private UserChallengeIdDTO id;
    private User user;
    private Challenge challenge;
    private Double currentAmount;
}
