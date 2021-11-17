package com.herokuapp.runningappbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;
//    @JsonIgnoreProperties("participants")
    private Set<UserChallengeDTO> userChallenges;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
}
