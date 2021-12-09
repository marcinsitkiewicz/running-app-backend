package com.herokuapp.runningappbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityDTO {

    private Long id;
    @JsonIgnore
    private UserDTO user;
    @JsonIgnore
    private PostDTO postActivity;
    @JsonIgnore
    private UserChallengeDTO userChallenge;
    private LocalDateTime date;
    private ImageDTO mapImage;
    private String totalTime;
    private int calories;
    private int distance;
    private String pace;
    private Float speed;
}
