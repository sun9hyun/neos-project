package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.service.study.StudyMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/study-member/*")
public class StudyMemberController {
    private final StudyMemberService studyMemberService;
    @PostMapping("/join")
    public RedirectView join(StudySupporterDTO supporterDTO){
        studyMemberService.supportJoin(supporterDTO);
        return new RedirectView("/study/list/"+supporterDTO.getStudyId());
    }
}
