package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ActivityDTO {

    private Long id;
    private UserDTO user;
//    @JsonIgnore
//    private PostDTO postActivity;
    private LocalDateTime date;
    private ImageDTO mapImage;
    private String totalTime;
    private int calories;
    private int distance;
    private String pace;
    private Float speed;
    private Boolean isPosted;
    private Set<UserDTO> likes = new HashSet<>();
}
