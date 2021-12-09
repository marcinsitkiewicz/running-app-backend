package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.model.Image;
import com.herokuapp.runningappbackend.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImageServiceImpl {

    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    public ImageServiceImpl(ImageRepository imageRepository,
                            ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    public Image create(MultipartFile file) {
        Image image = new Image();
        try {
            image.setData(Base64.getEncoder().encode(file.getBytes()));
            imageRepository.save(image);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
