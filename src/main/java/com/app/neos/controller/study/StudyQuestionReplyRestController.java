package com.app.neos.controller.study;


import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.service.study.StudyQuestionReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/quest-reply/")
@RequiredArgsConstructor
public class StudyQuestionReplyRestController {
    private final StudyQuestionReplyService studyQuestionReplyService;

    @PostMapping("/{bno}")
    public String post(@PathVariable("bno")Long studyQuestionId, StudyQuestionReplyDTO studyQuestionReplyDTO, Long userId){
        studyQuestionReplyService.post(studyQuestionId,studyQuestionReplyDTO,userId);
        return "success";
    }


    @GetMapping("/{bno}")
    public List<StudyQuestionReplyDTO> getList(@PathVariable("bno") Long studyQuestionId){
        return studyQuestionReplyService.showList(studyQuestionId);
    }


}
