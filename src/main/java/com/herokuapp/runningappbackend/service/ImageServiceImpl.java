package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.ImageDTO;
import com.herokuapp.runningappbackend.exception.NoDataException;
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

    public Image create(MultipartFile file, String fileType) {
        Image image = new Image();
        try {
            image.setData(Base64.getEncoder().encode(file.getBytes()));
            image.setImageType(fileType);
            imageRepository.save(image);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageDTO imageToImageDTO(Image image) {
        if (image == null) {
            return null;
        }
        ImageDTO imageDTO = modelMapper.map(image, ImageDTO.class);
        byte[] imageData = image.getData();
        if (imageData != null && imageData.length > 0) {
            imageDTO.setData(new String(imageData));
        } else {
            imageDTO.setData(null);
        }
        return imageDTO;
    }

    public ImageDTO get(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(NoDataException::new);
        return imageToImageDTO(image);
    }
}
