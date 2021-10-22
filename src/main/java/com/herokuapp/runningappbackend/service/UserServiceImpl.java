package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.UserDTO;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IService<UserDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Collection<UserDTO> getAll() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "userId"));

        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public Optional<UserDTO> get(Long id) {
        Optional<User> user = userRepository.findById(id);

        return modelMapper.map(user, new TypeToken<Optional<UserDTO>>(){}.getType());
    }
}
