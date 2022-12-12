package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyWorkDTO;
import com.app.neos.service.study.StudyWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/study-work/*")
public class StudyWorkController {
    private final StudyWorkService studyWorkService;
    @PostMapping("/post")
    public RedirectView post(StudyWorkDTO studyWorkDTO, String studyWorkTargetDateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(studyWorkTargetDateString, formatter);
        studyWorkDTO.setStudyWorkTargetDate(date);
        studyWorkService.post(studyWorkDTO);
        return new RedirectView("/study/work/"+studyWorkDTO.getStudy().getStudyId());
    }

    @PostMapping("/complete")
    public RedirectView complete(StudyWorkDTO studyWorkDTO, Long studyId){
        studyWorkService.complete(studyWorkDTO);
        return new RedirectView("/study/work/"+studyId);
    }
}
