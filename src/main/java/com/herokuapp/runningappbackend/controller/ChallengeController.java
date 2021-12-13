package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import com.herokuapp.runningappbackend.dto.UserChallengeDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.dto.form.ChallengeFormDTO;
import com.herokuapp.runningappbackend.model.Challenge;
import com.herokuapp.runningappbackend.service.ChallengeServiceImpl;
import com.herokuapp.runningappbackend.service.UserChallengeServiceImpl;
import com.herokuapp.runningappbackend.service.UserServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ChallengeController {

    private final ChallengeServiceImpl challengeService;
    private final UserServiceImpl userService;
    private final UserChallengeServiceImpl userChallengeService;

    public ChallengeController(ChallengeServiceImpl challengeService,
                               UserServiceImpl userService,
                               UserChallengeServiceImpl userChallengeService) {
        this.challengeService = challengeService;
        this.userService = userService;
        this.userChallengeService = userChallengeService;
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

    @GetMapping("/challenges/query")
    public ResponseEntity<Collection<ChallengeDTO>> getAllChallenges(@SearchSpec Specification<Challenge> specs) {
        try {
            Collection<ChallengeDTO> challengesDTO = challengeService.queryAll(specs);

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

    @GetMapping("/challenges/user/{userId}")
    public ResponseEntity<Collection<ChallengeDTO>> getAllChallengesByUser(@PathVariable("userId") Long userId) {
        try {
            UserDTO userDTO = userService.get(userId);
            Collection<UserChallengeDTO> userChallengesDTO = userChallengeService.getAllByUser(userDTO);

            Collection<ChallengeDTO> challengeDTOS = new ArrayList<>();
            for (UserChallengeDTO userChallengeDTO: userChallengesDTO) {
                challengeDTOS.add(userChallengeDTO.getChallenge());
            }

            if (challengeDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(challengeDTOS, HttpStatus.OK);
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
