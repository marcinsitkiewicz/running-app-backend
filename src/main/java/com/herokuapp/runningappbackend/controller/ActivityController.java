package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.ActivityDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.dto.form.ActivityFormDTO;
import com.herokuapp.runningappbackend.model.Activity;
import com.herokuapp.runningappbackend.service.ActivityServiceImpl;
import com.herokuapp.runningappbackend.service.UserServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ActivityController {

    private final ActivityServiceImpl activityService;
    private final UserServiceImpl userService;

    public ActivityController(ActivityServiceImpl activityService,
                              UserServiceImpl userService) {
        this.activityService = activityService;
        this.userService = userService;
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
    public ResponseEntity<ActivityDTO> addActivity(@RequestBody ActivityFormDTO activityFormDTO) {
        activityService.create(activityFormDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
