package com.app.neos.aspect;


import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.service.alarm.AlarmService;
import com.app.neos.type.alarm.AlarmCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogAspect {
    private final AlarmService alarmService;


    @After("@annotation(com.app.neos.aspect.annotation.AlarmInput)")
    public void alarm(JoinPoint joinPoint){
        Object target = joinPoint.getArgs()[0];
        AlarmCategory category = (AlarmCategory)joinPoint.getArgs()[1];
        alarmService.alarmSend(target,category);
    }
}
