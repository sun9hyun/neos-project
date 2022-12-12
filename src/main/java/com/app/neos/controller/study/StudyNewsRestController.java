package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyNewsDTO;
import com.app.neos.domain.study.StudySearch;
import com.app.neos.service.study.StudyNewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/news/*")
public class StudyNewsRestController {
    private final StudyNewsService studyNewsService;

    @GetMapping("/{bno}")
    public Slice<StudyNewsDTO> getList(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable("bno") Long studyId){
        return  studyNewsService.showList(studyId,pageable);
    }
}
