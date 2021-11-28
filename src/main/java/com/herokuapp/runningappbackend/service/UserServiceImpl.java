package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.exception.NoDataException;
import com.herokuapp.runningappbackend.model.Challenge;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements IService<UserDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public Collection<UserDTO> getAll() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public UserDTO get(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoDataException::new);

        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional
    public UserDTO addChallenge(UserDTO userDTO, ChallengeDTO challengeDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Challenge challenge = modelMapper.map(challengeDTO, Challenge.class);
        User _user = userRepository.findById(user.getId()).orElseThrow(NoDataException::new);

//        _user.getUserChallenges().add(challenge);
        userRepository.save(_user);

        return modelMapper.map(_user, UserDTO.class);
    }

    public Collection<UserDTO> queryAll(Specification<User> specs) {
        List<User> users = userRepository.findAll(Specification.where(specs));

        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO create(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
        return null;
    }
}
