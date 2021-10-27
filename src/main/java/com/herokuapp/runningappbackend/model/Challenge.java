package com.herokuapp.runningappbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long challengeId;

    @ManyToMany(mappedBy = "challenges", fetch = FetchType.LAZY)
    private Set<User> participants = new HashSet<>();

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    private Double amountToComplete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
                timezone = "Europe/Warsaw")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
                timezone = "Europe/Warsaw")
    private LocalDateTime endDate;
}
