package com.herokuapp.runningappbackend.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Bean
    public WebClient localApiClient() {
        return WebClient.create(System.getenv("SERVER_API_URL"));
    }
}
