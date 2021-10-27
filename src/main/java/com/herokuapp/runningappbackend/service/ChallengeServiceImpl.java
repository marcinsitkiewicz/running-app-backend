package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import com.herokuapp.runningappbackend.dto.ChallengeFormDTO;
import com.herokuapp.runningappbackend.exception.NoDataException;
import com.herokuapp.runningappbackend.model.Challenge;
import com.herokuapp.runningappbackend.repository.ChallengeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ChallengeServiceImpl implements IService<ChallengeDTO>{

    private final ChallengeRepository challengeRepository;
    private final ModelMapper modelMapper;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository, ModelMapper modelMapper) {
        this.challengeRepository = challengeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Collection<ChallengeDTO> getAll() {
        List<Challenge> challenges = challengeRepository.findAll(Sort.by(Sort.Direction.ASC, "challengeId"));

        return modelMapper.map(challenges, new TypeToken<List<ChallengeDTO>>(){}.getType());
    }

    @Override
    public ChallengeDTO get(Long id) {
        Challenge challenge = challengeRepository.findById(id).orElseThrow(NoDataException::new);

        return modelMapper.map(challenge, ChallengeDTO.class);
    }

    public ChallengeFormDTO create(ChallengeFormDTO challengeFormDTO) {
        Challenge challenge = modelMapper.map(challengeFormDTO, Challenge.class);
        challengeRepository.save(challenge);

        return challengeFormDTO;
    }
}
