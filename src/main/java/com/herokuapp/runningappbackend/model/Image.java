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

    @OneToOne(mappedBy = "activityImage")
    private Activity activity;

    @OneToOne(mappedBy = "userImage")
    private User user;
}
