package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.ActivityDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.service.ActivityServiceImpl;
import com.herokuapp.runningappbackend.service.ChallengeServiceImpl;
import com.herokuapp.runningappbackend.service.LikeServiceImpl;
import com.herokuapp.runningappbackend.service.UserServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final ChallengeServiceImpl challengeService;
    private final ActivityServiceImpl activityService;
    private final LikeServiceImpl likeService;

    public UserController(UserServiceImpl userService,
                          ChallengeServiceImpl challengeService,
                          ActivityServiceImpl activityService,
                          LikeServiceImpl likeService) {
        this.userService = userService;
        this.challengeService = challengeService;
        this.activityService = activityService;
        this.likeService = likeService;
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<UserDTO>> getAllUsers() {
        try {
            Collection<UserDTO> users = userService.getAll();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/query")
    public ResponseEntity<Collection<UserDTO>> queryAllUsers(@SearchSpec Specification<User> specs) {
        try {
            Collection<UserDTO> users = userService.queryAll(specs);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        try {
            UserDTO user = userService.get(id);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/users/{userId}/like-activity/{activityId}")
    public ResponseEntity<UserDTO> likeActivity(@PathVariable("userId") Long userId,
                                                @PathVariable("activityId") Long activityId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-monthly-distance/user/{id}")
    public ResponseEntity<Long> getDistanceByUserId(@PathVariable("id") Long id) {
        try {
            UserDTO user = userService.get(id);
            Collection<ActivityDTO> activitiesDTO = activityService.getAllByUser(user);
            long distance = userService.getDistance(activitiesDTO);
            return new ResponseEntity<>(distance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
