package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudySearch;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.join.JoinService;
import com.app.neos.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/study/*")
@RequiredArgsConstructor
@Slf4j
public class StudyController {
    private final UserRepository userRepository;
    private final StudyService studyService;
    private final JoinService joinService;


    @GetMapping("/create")
    public String create(StudyDTO studyDTO, HttpServletRequest request, Model model){
        HttpSession session = (HttpSession)request.getSession();
        Long userId = (Long)session.getAttribute("loginUser");
        model.addAttribute("collegeName",userRepository.findById(userId).get().toDTO().getCollegeName());
        return "app/study/createStudy";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("collegeCityList",joinService.getCollegeCityList());
//        model.addAttribute("pagination",studyService.getPagination(pageable));
//        model.addAttribute("studyList",studyService.getBoardList(pageable));
        return "app/study/listStudy";
    }

    @GetMapping("/detail")
    public String detail(){
        return "app/study/detail-study";
    }

    @GetMapping("/question")
    public String question(){
        return "app/study/questionStudy";
    }

    @GetMapping("/feed")
    public String feed(){
        return "app/study/feed-study";
    }

    @GetMapping("/management")
    public String management(){
        return "app/study/studyManagement";
    }

    @GetMapping("/work")
    public String work(){
        return "app/study/workStudy";
    }



    @GetMapping("/test")
    public String test(){
        return "app/study/test";
    }

    @PostMapping("/ok")
    public RedirectView post(StudyDTO studyDTO){
        studyService.post(studyDTO);
        return new RedirectView("/study/list");
    }

    @GetMapping("/list/{studyId}")
    public String listDetail(@PathVariable Long studyId, Model model){
        StudyDTO study = studyService.getStudyDTO(studyId);
        UserDTO user = studyService.getInfo(study.getUserId());
        model.addAttribute("writer",user);
        model.addAttribute("study",study);
        return "app/study/detail-study";
    }

}
