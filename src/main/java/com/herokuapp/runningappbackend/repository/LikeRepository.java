package com.herokuapp.runningappbackend.repository;

import com.herokuapp.runningappbackend.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LikeRepository  extends JpaRepository<Like, Long>, JpaSpecificationExecutor<Like> {
    List<Like> findAllByUserId(Long userId);
    List<Like> findAllByActivityId(Long activityId);
}
