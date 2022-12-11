package com.app.neos.controller.study;


import com.app.neos.domain.study.StudyMilestoneDTO;
import com.app.neos.service.study.StudyMileStoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mile-stone/*")
@Slf4j
@RequiredArgsConstructor
public class StudyMileStoneRestController {
    private final StudyMileStoneService studyMileStoneService;


    @PostMapping("/{bno}")
    public String mileStonePost(@PathVariable("bno") Long studyId, StudyMilestoneDTO studyMilestoneDTO, Long userId){

        studyMileStoneService.post(studyId,studyMilestoneDTO,userId);

        return "success";
    }

    @GetMapping("/{bno}")
    public List<StudyMilestoneDTO> mileStoneList(@PathVariable("bno") Long studyId){

        return studyMileStoneService.showListProceeding(studyId);
    }

    @GetMapping("/finish/{bno}")
    public List<StudyMilestoneDTO> mileStoneFinishList(@PathVariable("bno") Long studyId){
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return studyMileStoneService.showListFinish(studyId);
    }
}
