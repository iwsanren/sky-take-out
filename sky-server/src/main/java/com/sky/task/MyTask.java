package com.sky.task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Customize scheduled task class
 */
//@Component
@Slf4j
public class MyTask {

    /**
     * Scheduled Task Business Logic Related Code
     */

    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask(){
        log.info("Scheduled task begin to execute: {}", new Date());

    }
}
