package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import com.herokuapp.runningappbackend.dto.UserChallengeDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.model.UserChallenge;
import com.herokuapp.runningappbackend.service.ChallengeServiceImpl;
import com.herokuapp.runningappbackend.service.UserChallengeServiceImpl;
import com.herokuapp.runningappbackend.service.UserServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserChallengeController {

    private final UserChallengeServiceImpl userChallengeService;
    private final UserServiceImpl userService;
    private final ChallengeServiceImpl challengeService;

    public UserChallengeController(UserChallengeServiceImpl userChallengeService, UserServiceImpl userService, ChallengeServiceImpl challengeService) {
        this.userChallengeService = userChallengeService;
        this.userService = userService;
        this.challengeService = challengeService;
    }

    @GetMapping("/user-challenges")
    public ResponseEntity<Collection<UserChallengeDTO>> getAllChallenges() {
        try {
            Collection<UserChallengeDTO> userChallengesDTO = userChallengeService.getAll();

            if (userChallengesDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(userChallengesDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user-challenges/query")
    public ResponseEntity<Collection<UserChallengeDTO>> getAllActivities(@SearchSpec Specification<UserChallenge> specs) {
        try {
            Collection<UserChallengeDTO> userChallengesDTO = userChallengeService.queryAll(specs);

            if (userChallengesDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(userChallengesDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/{userId}/join/{challengeId}")
    public ResponseEntity<UserChallengeDTO> addUserToChallenge(@PathVariable("userId") Long userId,
                                                               @PathVariable("challengeId") Long challengeId) {
        try {
            UserDTO user = userService.get(userId);
            ChallengeDTO challenge = challengeService.get(challengeId);

            UserChallengeDTO userChallengeDTO = userChallengeService.add(user, challenge);

            return new ResponseEntity<>(userChallengeDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
