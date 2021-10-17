package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
