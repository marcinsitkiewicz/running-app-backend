package com.herokuapp.runningappbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herokuapp.runningappbackend.model.Activity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;
//    @JsonIgnoreProperties("participants")
    @JsonIgnore
    private Set<UserChallengeDTO> userChallenges;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Date birthDate;
    @JsonIgnore
    private Set<Activity> likedActivities;
}
