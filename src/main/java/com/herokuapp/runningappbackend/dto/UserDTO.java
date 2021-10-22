package com.herokuapp.runningappbackend.dto;

import com.herokuapp.runningappbackend.model.Challenge;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    private Set<Challenge> challenges;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
