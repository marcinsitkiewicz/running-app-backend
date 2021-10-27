package com.herokuapp.runningappbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    @JsonIgnoreProperties("participants")
    private Set<ChallengeDTO> challenges;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
}
