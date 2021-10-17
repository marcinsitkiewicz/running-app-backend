package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.configuration.AppConfig;
import com.herokuapp.runningappbackend.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements IService<UserDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, AppConfig appConfig) {
        this.userRepository = userRepository;
        this.modelMapper = appConfig.modelMapper();
    }

    @Override
    public Collection<UserDTO> getAll() {
        return modelMapper.map(userRepository.findAll(Sort.by(Sort.Direction.ASC, "userId")),
                                new TypeToken<List<UserDTO>>(){}.getType());
    }
}
