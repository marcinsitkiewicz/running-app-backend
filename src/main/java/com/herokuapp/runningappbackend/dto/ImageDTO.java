package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {

    private long id;
    private String data;
    private String imageType;
//    @JsonIgnore
//    private ActivityDTO activity;
//    @JsonIgnore
//    private UserDTO user;
}
