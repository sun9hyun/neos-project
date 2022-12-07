package com.app.neos.controller.study;


import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudySearch;
import com.app.neos.entity.study.Study;
import com.app.neos.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/stu/")
public class StudyRestController {
    private final StudyService studyService;

    @GetMapping("/list")
    public Page<StudyDTO> getList(@PageableDefault(size = 16, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, StudySearch studySearch){
        log.info("여기보세요:"+studySearch.toString());
        return studyService.getBoardList(pageable,studySearch);
    }

    @GetMapping("/recent")
    public List<StudyDTO> getRecent(){
        return studyService.getStudyListUntilFour();
    }

    @PutMapping("/college")
    public List<String> getCollegeNames(){
        return studyService.collegeName();
    }

}
