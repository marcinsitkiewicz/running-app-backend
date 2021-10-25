package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import com.herokuapp.runningappbackend.service.ChallengeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ChallengeController {

    private final ChallengeServiceImpl challengeService;

    public ChallengeController(ChallengeServiceImpl challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<Collection<ChallengeDTO>> getAllChallenges() {
        try {
            Collection<ChallengeDTO> challenges = challengeService.getAll();

            if (challenges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(challenges, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/challenges/{id}")
    public ResponseEntity<ChallengeDTO> getChallengeById(@PathVariable("id") Long id) {
        try {
            ChallengeDTO challenge = challengeService.get(id);

            if (challenge == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(challenge, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
