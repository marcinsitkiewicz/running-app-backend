package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.ActivityDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.exception.NoDataException;
import com.herokuapp.runningappbackend.model.Activity;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ActivityServiceImpl implements IService<ActivityDTO> {

    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository,
                               ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
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
}
