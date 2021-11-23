package com.herokuapp.runningappbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {

    private long id;
    private byte[] data;
    private String imageType;
    private PostDTO post;
    private UserDTO user;
}
