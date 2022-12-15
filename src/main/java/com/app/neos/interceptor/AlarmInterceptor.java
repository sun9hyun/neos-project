package com.app.neos.interceptor;


import com.app.neos.entity.alarm.Alarm;
import com.app.neos.repository.alarm.AlarmCustomRepository;
import com.app.neos.repository.alarm.AlarmRepository;
import com.app.neos.service.alarm.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmInterceptor implements HandlerInterceptor {
    private final AlarmCustomRepository alarmCustomRepository;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("loginUser");
        if(userId != null){
            List<Alarm> list = alarmCustomRepository.findAllNoReadAlarmByUserId(userId);

            if(list.size()>0){
                modelAndView.addObject("checkAlarm", true);
            }else{
//                modelAndView.addObject("checkAlarm", false);
            }

        }
    }
}
