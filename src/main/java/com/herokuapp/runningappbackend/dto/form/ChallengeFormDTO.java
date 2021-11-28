package com.herokuapp.runningappbackend.dto.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChallengeFormDTO {

    private String name;
    private String description;
    private Double amountToComplete;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
