package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class PostDTO {
    
    private Long id;
    private UserDTO postAuthor;
    private LocalDateTime postedDate;
    private Set<UserDTO> likes;
    private ActivityDTO activity;
}
