package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.service.ChallengeServiceImpl;
import com.herokuapp.runningappbackend.service.UserServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final ChallengeServiceImpl challengeService;

    public UserController(UserServiceImpl userService, ChallengeServiceImpl challengeService) {
        this.userService = userService;
        this.challengeService = challengeService;
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

}
