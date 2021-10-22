package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long challengeId;

    @ManyToMany(mappedBy = "challenges", fetch = FetchType.LAZY)
    private Set<User> participants = new HashSet<>();

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private Double amountToComplete;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}
