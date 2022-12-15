package com.app.neos.controller.alarm;


import com.app.neos.domain.Alarm.AlarmDTO;
import com.app.neos.service.alarm.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/alarm/*")
@Slf4j
@RequiredArgsConstructor
public class AlarmRestController {
    private final AlarmService alarmService;


    @GetMapping("/header")
    public List<AlarmDTO> showNoReadList(HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        return alarmService.showNoRead(userId);
    }

    @PostMapping("/{bno}")
    public String read(@PathVariable("bno") Long alarmId){
        String url = alarmService.read(alarmId);
        return url;
    }
}
