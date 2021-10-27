package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import com.herokuapp.runningappbackend.dto.ChallengeFormDTO;
import com.herokuapp.runningappbackend.service.ChallengeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            Collection<ChallengeDTO> challengesDTO = challengeService.getAll();

            if (challengesDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(challengesDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/challenges/{id}")
    public ResponseEntity<ChallengeDTO> getChallengeById(@PathVariable("id") Long id) {
        try {
            ChallengeDTO challengeDTO = challengeService.get(id);

            if (challengeDTO == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(challengeDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-challenge")
    public ResponseEntity<ChallengeFormDTO> addChallenge(@RequestBody ChallengeFormDTO challengeFormDTO) {
        challengeFormDTO = challengeService.create(challengeFormDTO);

        return new ResponseEntity<>(challengeFormDTO, HttpStatus.OK);
    }
}
