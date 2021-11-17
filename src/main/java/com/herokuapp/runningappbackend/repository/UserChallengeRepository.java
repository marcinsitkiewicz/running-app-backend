package com.herokuapp.runningappbackend.repository;

import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long>, JpaSpecificationExecutor<User> {
}
