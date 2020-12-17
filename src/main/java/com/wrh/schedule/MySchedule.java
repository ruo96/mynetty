package com.wrh.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname MySchedule
 * @Description TODO
 * @Date 2020/12/17 15:39
 */
@Slf4j
@Component
public class MySchedule {

//    @Scheduled(fixedDelay = 1000)
//    public void fixedDelay(){
//        log.info(">>> fixedDelay: " + LocalDateTime.now().toString());
//    }

//    @Scheduled(cron = "0 * * * * ?")
    public void cron(){
        log.info(">>> cron: " + LocalDateTime.now().toString());
    }
}
