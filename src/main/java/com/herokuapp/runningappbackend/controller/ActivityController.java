package com.herokuapp.runningappbackend.controller;

import com.google.gson.Gson;
import com.herokuapp.runningappbackend.dto.ActivityDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.dto.form.ActivityFormDTO;
import com.herokuapp.runningappbackend.model.Activity;
import com.herokuapp.runningappbackend.model.Image;
import com.herokuapp.runningappbackend.service.ActivityServiceImpl;
import com.herokuapp.runningappbackend.service.ImageServiceImpl;
import com.herokuapp.runningappbackend.service.UserServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {

    private final ActivityServiceImpl activityService;
    private final ImageServiceImpl imageService;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    public ActivityController(ActivityServiceImpl activityService,
                              ImageServiceImpl imageService,
                              UserServiceImpl userService,
                              ModelMapper modelMapper) {
        this.activityService = activityService;
        this.imageService = imageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/activities")
    public ResponseEntity<Collection<ActivityDTO>> getAllActivities() {
        try {
            Collection<ActivityDTO> activitiesDTO = activityService.getAll();

            if (activitiesDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(activitiesDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activities/query")
    public ResponseEntity<Collection<ActivityDTO>> getAllActivities(@SearchSpec Specification<Activity> specs) {
        try {
            Collection<ActivityDTO> activitiesDTO = activityService.queryAll(specs);

            if (activitiesDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                Collections.reverse((List<?>) activitiesDTO);
                return new ResponseEntity<>(activitiesDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long id) {
        try {
            ActivityDTO activityDTO = activityService.get(id);

            if (activityDTO == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(activityDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activities/user/{id}")
    public ResponseEntity<Collection<ActivityDTO>> getActivitiesByUserId(@PathVariable("id") Long id) {
        try {
            UserDTO user = userService.get(id);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                Collection<ActivityDTO> activitiesDTO = activityService.getAllByUser(user);

                if (activitiesDTO.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(activitiesDTO, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-activity")
    public ResponseEntity<ActivityDTO> addActivity(@RequestParam(name = "mapFile") MultipartFile file,
                                                   @RequestParam(name = "requestBody") String activityFormDTOString) {
        // TODO: change back from Gson to ModelMapper
        Gson gson = new Gson();
        ActivityFormDTO activityFormDTO = gson.fromJson(activityFormDTOString, ActivityFormDTO.class);
        activityService.create(activityFormDTO, file);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-image")
    public ResponseEntity<ActivityDTO> addImage(@RequestParam(name = "mapFile") MultipartFile file) {
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Image image = imageService.create(file, "activity_image");

        if (image == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/change-activity-post-status/{id}")
    public ResponseEntity<ActivityDTO> changeActivityPostStatus(@PathVariable("id") Long id) {
        activityService.changePostStatus(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
