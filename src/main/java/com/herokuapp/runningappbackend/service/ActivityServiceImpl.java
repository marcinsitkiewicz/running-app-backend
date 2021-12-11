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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Transactional
    public Collection<ActivityDTO> getAll() {
        List<Activity> activities = activityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return getActivityDTOS(activities);
    }

    @Override
    @Transactional
    public ActivityDTO get(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(NoDataException::new);

        return activityToActivityDTO(activity);
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

    public void create(ActivityFormDTO activityFormDTO, MultipartFile file) {
        User user = userRepository.findById(activityFormDTO.getUserId()).orElseThrow(NoDataException::new);
        Image image = imageService.create(file, "activity_image");
        Activity activity = new Activity(
                user,
                LocalDateTime.now(),
                image,
                activityFormDTO.getTotalTime(),
                activityFormDTO.getCalories(),
                activityFormDTO.getDistance(),
                activityFormDTO.getPace(),
                activityFormDTO.getSpeed(),
                false
        );
        activityRepository.save(activity);
    }

    public void changePostStatus(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(NoDataException::new);

        if (!activity.getIsPosted() || activity.getIsPosted() == null)
            activity.setIsPosted(true);
        else if (activity.getIsPosted())
            activity.setIsPosted(false);

        activityRepository.save(activity);
    }

    public ActivityDTO activityToActivityDTO(Activity activity) {
        ActivityDTO activityDTO = modelMapper.map(activity, ActivityDTO.class);
        activityDTO.setMapImage(imageService.imageToImageDTO(activity.getMapImage()));
        return activityDTO;
    }

    private Collection<ActivityDTO> getActivityDTOS(Collection<Activity> activities) {
        List<ActivityDTO> activitiesDTO = new ArrayList<>();
        for (Activity activity : activities) {
            activitiesDTO.add(activityToActivityDTO(activity));
        }
        return activitiesDTO;
    }
}
