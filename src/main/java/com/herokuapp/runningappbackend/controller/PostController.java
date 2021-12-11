package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.PostDTO;
import com.herokuapp.runningappbackend.model.Post;
import com.herokuapp.runningappbackend.service.ActivityServiceImpl;
import com.herokuapp.runningappbackend.service.PostServiceImpl;
import com.herokuapp.runningappbackend.service.UserServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostServiceImpl postService;
    private final UserServiceImpl userService;
    private final ActivityServiceImpl activityService;

    public PostController(PostServiceImpl postService,
                          UserServiceImpl userService,
                          ActivityServiceImpl activityService) {
        this.postService = postService;
        this.userService = userService;
        this.activityService = activityService;
    }

    @GetMapping("/posts")
    public ResponseEntity<Collection<PostDTO>> getAllPosts() {
        try {
            Collection<PostDTO> postsDTO = postService.getAll();

            if (postsDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(postsDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/posts/query")
    public ResponseEntity<Collection<PostDTO>> getAllPosts(@SearchSpec Specification<Post> specs) {
        try {
            Collection<PostDTO> postsDTO = postService.queryAll(specs);

            if (postsDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(postsDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getChallengeById(@PathVariable("id") Long id) {
        try {
            PostDTO postDTO = postService.get(id);

            if (postDTO == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(postDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-post/user/{userId}/activity/{activityId}")
    public ResponseEntity<PostDTO> addPost(@PathVariable("userId") Long userId,
                                           @PathVariable("activityId") Long activityId) {
//        postService.create(activityId, userId);

        PostDTO postDTO = new PostDTO();
        postDTO.setPostAuthor(userService.get(userId));
        postDTO.setActivity(activityService.get(activityId));
        postService.create(postDTO);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }
}
