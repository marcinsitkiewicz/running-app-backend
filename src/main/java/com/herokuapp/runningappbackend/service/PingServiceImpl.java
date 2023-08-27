package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.ChallengeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class PingServiceImpl {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient localApiClient;

    @Autowired
    public PingServiceImpl(WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    public void getChallenge(long id) {
        localApiClient
                .get()
                .uri("/challenges/" + id)
                .retrieve()
                .bodyToMono(ChallengeDTO.class)
                .block(REQUEST_TIMEOUT);
    }

}
