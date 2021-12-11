package com.herokuapp.runningappbackend.controller;

import com.herokuapp.runningappbackend.dto.ImageDTO;
import com.herokuapp.runningappbackend.service.ImageServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImageController {

    private final ImageServiceImpl imageService;
    private final ModelMapper modelMapper;

    public ImageController(ImageServiceImpl imageService,
                           ModelMapper modelMapper) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<ImageDTO> getImageById(@PathVariable("id") Long id) {
        try {
            ImageDTO imageDTO = imageService.get(id);

            if (imageDTO == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(imageDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
