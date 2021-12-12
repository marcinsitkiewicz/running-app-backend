package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikeDTO {

    private Long userId;
    private Long activityId;
}
