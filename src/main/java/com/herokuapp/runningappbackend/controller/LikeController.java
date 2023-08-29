package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.LikeDTO;
import com.herokuapp.runningappbackend.model.Like;
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
public class LikeController {

    private final LikeServiceImpl likeService;
    private final UserServiceImpl userService;

    public LikeController(LikeServiceImpl likeService,
                          UserServiceImpl userService) {
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("/likes")
    public ResponseEntity<Collection<LikeDTO>> getAllLikes() {
        try {
            Collection<LikeDTO> likes = likeService.getAll();

            if (likes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(likes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/likes/query")
    public ResponseEntity<Collection<LikeDTO>> queryAllLikes(@SearchSpec Specification<Like> specs) {
        try {
            Collection<LikeDTO> likes = likeService.queryAll(specs);

            if (likes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(likes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/likes/{id}")
    public ResponseEntity<LikeDTO> getLikeById(@PathVariable("id") Long id) {
        try {
            LikeDTO like = likeService.get(id);

            if (like == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(like, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/likes/user/{id}")
    public ResponseEntity<Collection<LikeDTO>> getLikeByUserId(@PathVariable("id") Long userId) {
        try {
            Collection<LikeDTO> likes = likeService.getAllByUserId(userId);

            if (likes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(likes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "update-like/user/{userId}/activity/{activityId}",
            method={RequestMethod.POST,RequestMethod.DELETE})
    public ResponseEntity<LikeDTO> updateLike(@PathVariable("userId") Long userId,
                                              @PathVariable("activityId") Long activityId) {
        try {
            likeService.update(userId, activityId);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
