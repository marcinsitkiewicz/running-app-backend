package com.herokuapp.runningappbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] data;

    private String imageType;

    @OneToOne(mappedBy = "postImage")
    private Post post;

    @OneToOne(mappedBy = "userImage")
    private User user;
}
