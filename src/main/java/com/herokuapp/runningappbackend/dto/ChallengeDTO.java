package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ChallengeDTO {

    private Long id;
//    @JsonIgnoreProperties("challenges")
    private Set<UserChallengeDTO> userChallenges;
    private String name;
    private String description;
    private Double amountToComplete;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
