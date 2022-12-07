package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.service.study.StudyQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/quest/")
@RequiredArgsConstructor
public class StudyQuestionRestController {
    private final StudyQuestionService studyQuestionService;

    @DeleteMapping("/{bno}")
    public String delete(@PathVariable("bno") Long studyQuestionId){
            studyQuestionService.remove(studyQuestionId);
        return "delete success";
    }

    @PostMapping("/{bno}")
    public StudyQuestionDTO update(@PathVariable("bno") Long studyQuestionId, @RequestParam String content){
        studyQuestionService.update(studyQuestionId,content);
        return studyQuestionService.updateDTO(studyQuestionId);
    }



}
