package com.herokuapp.runningappbackend.task;

import com.herokuapp.runningappbackend.service.PingServiceImpl;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
public class PingTask {

    private final PingServiceImpl pingService;

    public PingTask(PingServiceImpl pingService) {
        this.pingService = pingService;
    }

    @Scheduled(initialDelay = 1000*10, fixedRate = 1000*60*5)
    public void run() {
        pingService.getChallenge(1);
        log.info("----------PINGED SERVER----------");
    }
}