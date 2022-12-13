package com.app.neos.controller.study;

import com.app.neos.service.study.StudyFollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/study-follow/*")
public class StudyFollowRestController {
    private final StudyFollowService studyFollowService;

    @PostMapping("/follow")
    public String follow(Long myId, Long studyId, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        boolean check = studyFollowService.follow(myId,studyId,userId);
        if(check){
            return "duplicated";
        }
        return "success";
    }



}
