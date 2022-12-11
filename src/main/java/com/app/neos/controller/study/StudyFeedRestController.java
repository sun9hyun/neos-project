package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyFeedDTO;
import com.app.neos.service.study.StudyFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/study-feed/")
@RequiredArgsConstructor
public class StudyFeedRestController {
    private final StudyFeedService studyFeedService;
    @GetMapping("/show-list/{bno}")
    public List<StudyFeedDTO> showList(@PathVariable("bno") Long studyId){
        return studyFeedService.showList(studyId);
    }

    @DeleteMapping("/{bno}")
    public String delete(@PathVariable("bno") Long studyFeedId){
        studyFeedService.remove(studyFeedId);
        return "delete success";
    }

    @PostMapping("/{bno}")
    public String update(@PathVariable("bno") Long studyFeedId,StudyFeedDTO studyFeedDTO){
        studyFeedDTO.setStudyFeedId(studyFeedId);
        studyFeedService.update(studyFeedDTO);
        return "update success";
    }

}
