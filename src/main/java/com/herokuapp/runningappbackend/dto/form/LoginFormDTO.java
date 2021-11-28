package com.herokuapp.runningappbackend.dto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginFormDTO {

    @NotBlank
    private String username;

    @JsonProperty(value = "plainPassword")
    private String password;
}
