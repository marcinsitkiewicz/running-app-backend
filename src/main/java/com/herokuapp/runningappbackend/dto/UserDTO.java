package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    private Set<ChallengeDTO> challenges;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
