package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityDTO {

    private Long id;
    private UserDTO user;
    private LocalDateTime date;
    private ImageDTO mapImage;
    private String totalTime;
    private int calories;
    private Double distance;
    private String pace;
    private Float speed;
    private Boolean isPosted;
    private int likesAmount;
    private LocalDateTime datePosted;
}
