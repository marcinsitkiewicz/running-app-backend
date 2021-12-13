package com.herokuapp.runningappbackend.repository;

import com.herokuapp.runningappbackend.model.Challenge;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long>, JpaSpecificationExecutor<UserChallenge> {
    Collection<UserChallenge> findAllByUser(User user);
    Collection<UserChallenge> getAllByUserAndIsCompletedIsFalse(User user);
    Collection<UserChallenge> getAllByChallenge(Challenge challenge);
}
