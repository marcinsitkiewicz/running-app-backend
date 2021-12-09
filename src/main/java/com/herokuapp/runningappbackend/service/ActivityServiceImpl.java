package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.ActivityDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.dto.form.ActivityFormDTO;
import com.herokuapp.runningappbackend.exception.NoDataException;
import com.herokuapp.runningappbackend.model.Activity;
import com.herokuapp.runningappbackend.model.Image;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.repository.ActivityRepository;
import com.herokuapp.runningappbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class ActivityServiceImpl implements IService<ActivityDTO> {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ImageServiceImpl imageService;
    private final ModelMapper modelMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository,
                               UserRepository userRepository,
                               ImageServiceImpl imageService,
                               ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Collection<ActivityDTO> getAll() {
        List<Activity> activities = activityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return modelMapper.map(activities, new TypeToken<List<ActivityDTO>>(){}.getType());
    }

    @Override
    public ActivityDTO get(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(NoDataException::new);

        return modelMapper.map(activity, ActivityDTO.class);
    }

    public Collection<ActivityDTO> queryAll(Specification<Activity> specs) {
        List<Activity> activities = activityRepository.findAll(Specification.where(specs));

        return modelMapper.map(activities, new TypeToken<List<ActivityDTO>>(){}.getType());
    }

    public Collection<ActivityDTO> getAllByUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        List<Activity> activities = activityRepository.findAllByUser(user);

        return modelMapper.map(activities, new TypeToken<List<ActivityDTO>>(){}.getType());
    }

    public void create(ActivityFormDTO activityFormDTO) {
        User user = userRepository.getById(activityFormDTO.getUserId());
        Image image = imageService.create(activityFormDTO.getFile());
        Activity activity = new Activity(
                user,
                LocalDateTime.now(),
                image,
                activityFormDTO.getTotalTime(),
                activityFormDTO.getCalories(),
                activityFormDTO.getDistance(),
                activityFormDTO.getPace(),
                activityFormDTO.getSpeed());
        activityRepository.save(activity);
    }
}
