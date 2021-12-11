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
        List<UserChallenge> userChallanges = userChallengeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return modelMapper.map(userChallanges, new TypeToken<List<UserChallengeDTO>>(){}.getType());
    }

    public Collection<UserChallengeDTO> queryAll(Specification<UserChallenge> specs) {
        List<UserChallenge> userChallanges = userChallengeRepository.findAll(Specification.where(specs));

        return modelMapper.map(userChallanges, new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public UserChallengeDTO get(Long id) {
        return null;
    }

    public UserChallengeDTO add(UserDTO userDTO, ChallengeDTO challengeDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Challenge challenge = modelMapper.map(challengeDTO, Challenge.class);

        UserChallenge userChallenge = new UserChallenge(user, challenge, 0.0, LocalDateTime.now());
        userChallengeRepository.save(userChallenge);

        return modelMapper.map(userChallenge, UserChallengeDTO.class);
    }
}
