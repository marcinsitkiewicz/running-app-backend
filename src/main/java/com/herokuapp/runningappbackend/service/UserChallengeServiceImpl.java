package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import com.herokuapp.runningappbackend.dto.UserChallengeDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.model.Challenge;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.model.UserChallenge;
import com.herokuapp.runningappbackend.repository.UserChallengeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserChallengeServiceImpl implements IService<UserChallengeDTO> {

    private final UserChallengeRepository userChallengeRepository;
    private final ModelMapper modelMapper;

    public UserChallengeServiceImpl(UserChallengeRepository userChallengeRepository, ModelMapper modelMapper) {
        this.userChallengeRepository = userChallengeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Collection<UserChallengeDTO> getAll() {
        List<UserChallenge> userChallenges = userChallengeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return modelMapper.map(userChallenges, new TypeToken<List<UserChallengeDTO>>(){}.getType());
    }

    public Collection<UserChallengeDTO> queryAll(Specification<UserChallenge> specs) {
        List<UserChallenge> userChallenges = userChallengeRepository.findAll(Specification.where(specs));

        return modelMapper.map(userChallenges, new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public UserChallengeDTO get(Long id) {
        return null;
    }

    public UserChallengeDTO add(UserDTO userDTO, ChallengeDTO challengeDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Challenge challenge = modelMapper.map(challengeDTO, Challenge.class);

        UserChallenge userChallenge = new UserChallenge(user, challenge, 0, LocalDateTime.now());
        userChallengeRepository.save(userChallenge);

        return modelMapper.map(userChallenge, UserChallengeDTO.class);
    }

    public Collection<UserChallengeDTO> getAllByUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        Collection<UserChallenge> userChallenges = userChallengeRepository.findAllByUser(user);
        return modelMapper.map(userChallenges, new TypeToken<List<UserChallengeDTO>>(){}.getType());
    }

    public void update(Collection<UserChallengeDTO> userChallengesDTO, int distance) {
        Collection<UserChallenge> userChallenges = modelMapper.map(userChallengesDTO, new TypeToken<Collection<UserChallenge>>(){}.getType());

        userChallenges.removeIf(UserChallenge::getIsCompleted);

        if (userChallenges.size() == 1) {
            ArrayList<UserChallenge> userChallengesArray = (ArrayList<UserChallenge>) userChallenges;
            UserChallenge userChallenge = userChallengesArray.get(0);

            if (userChallenge.getChallenge().getStartDate().isBefore(LocalDateTime.now()) &&
                    userChallenge.getChallenge().getEndDate().isAfter(LocalDateTime.now())) {

                userChallenge.setCurrentAmount(userChallenge.getCurrentAmount() + distance);

                if (userChallenge.getCurrentAmount() >= userChallenge.getChallenge().getAmountToComplete()) {
                    userChallenge.setIsCompleted(true);
                    userChallenge.setCompleteDate(LocalDateTime.now());
                }

                userChallengeRepository.save(userChallenge);
            }
        }
    }
}
