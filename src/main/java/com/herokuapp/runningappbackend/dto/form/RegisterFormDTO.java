package com.herokuapp.runningappbackend.dto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterFormDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty(value = "plainPassword")
    private String password;
}
