package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.LikeDTO;
import com.herokuapp.runningappbackend.exception.NoDataException;
import com.herokuapp.runningappbackend.model.Activity;
import com.herokuapp.runningappbackend.model.Like;
import com.herokuapp.runningappbackend.repository.ActivityRepository;
import com.herokuapp.runningappbackend.repository.LikeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LikeServiceImpl implements IService<LikeDTO> {

    private final LikeRepository likeRepository;
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public LikeServiceImpl(LikeRepository likeRepository,
                           ActivityRepository activityRepository,
                           ModelMapper modelMapper) {
        this.likeRepository = likeRepository;
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Collection<LikeDTO> getAll() {
        List<Like> likes = likeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return modelMapper.map(likes, new TypeToken<List<LikeDTO>>(){}.getType());
    }

    @Override
    public LikeDTO get(Long id) {
        Like like = likeRepository.findById(id).orElseThrow(NoDataException::new);

        return modelMapper.map(like, LikeDTO.class);
    }

    public Collection<LikeDTO> queryAll(Specification<Like> specs) {
        List<Like> likes = likeRepository.findAll(Specification.where(specs));

        return modelMapper.map(likes, new TypeToken<List<LikeDTO>>(){}.getType());
    }

    public Collection<LikeDTO> getAllByUserId(Long userId) {
        List<Like> likes = likeRepository.findAllByUserId(userId);

        return modelMapper.map(likes, new TypeToken<List<LikeDTO>>(){}.getType());
    }

    public void update(Long userId, Long activityId) {
        List<Like> alreadyLiked = likeRepository.findAllByUserId(userId);

        if (alreadyLiked.isEmpty()) {
            add(userId, activityId);
            return;
        }

        for (Like like: alreadyLiked) {
            if (like.getActivityId().equals(activityId)) {
                delete(like, activityId);
                return;
            }
        }

        add(userId, activityId);
    }

    public void add(Long userId, Long activityId) {
        Like like = new Like();
        like.setActivityId(activityId);
        like.setUserId(userId);
        likeRepository.save(like);

        Activity activity = activityRepository.findById(activityId).orElseThrow(NoDataException::new);
        activity.setLikesAmount(activity.getLikesAmount()+1);
        activityRepository.save(activity);
    }

    public void delete(Like like, Long activityId) {
        likeRepository.delete(like);

        Activity activity = activityRepository.findById(activityId).orElseThrow(NoDataException::new);
        activity.setLikesAmount(activity.getLikesAmount()-1);
        activityRepository.save(activity);
    }
}
