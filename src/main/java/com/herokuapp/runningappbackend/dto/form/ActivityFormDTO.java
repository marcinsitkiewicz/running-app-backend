package com.herokuapp.runningappbackend.dto.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityFormDTO {

    private Long userId;
    private String totalTime;
    private int calories;
    private int distance;
    private String pace;
    private Float speed;
}
