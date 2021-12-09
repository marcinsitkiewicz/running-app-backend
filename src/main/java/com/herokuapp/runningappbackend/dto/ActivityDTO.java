package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityDTO {

    private Long id;
    private UserDTO user;
    private PostDTO postActivity;
    private UserChallengeDTO userChallenge;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ImageDTO postImage;
}
