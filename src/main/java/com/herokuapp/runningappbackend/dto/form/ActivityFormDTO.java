package com.herokuapp.runningappbackend.dto.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ActivityFormDTO {

    private Long userId;
    private MultipartFile file;
    private String totalTime;
    private int calories;
    private int distance;
    private String pace;
    private Float speed;
}
