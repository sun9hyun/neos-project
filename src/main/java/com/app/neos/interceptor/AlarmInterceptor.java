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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Long userId = (Long) request.getSession().getAttribute("loginUser");
        if(userId != null){
            List<Alarm> list = alarmCustomRepository.findAllNoReadAlarmByUserId(userId);

            if(list.size()>0){
                request.getSession().setAttribute("checkAlarm",true);
                return true;
            }else{
                request.getSession().setAttribute("checkAlarm",false);
                return true;
            }
        }
        return true;
    }
}
