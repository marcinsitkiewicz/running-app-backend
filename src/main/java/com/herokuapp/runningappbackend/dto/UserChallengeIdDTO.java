package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChallengeIdDTO {

    private Long userId;
    private Long challengeId;
}
