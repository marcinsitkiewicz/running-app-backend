package com.herokuapp.runningappbackend.repository;

import com.herokuapp.runningappbackend.model.Activity;
import com.herokuapp.runningappbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
    List<Activity> findAllByUser(User user);
    List<Activity> findAllByUserOrderById(User user);
}
