package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyFeedDTO;
import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.domain.study.StudySearch;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.join.JoinService;
import com.app.neos.service.study.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/study/*")
@RequiredArgsConstructor
@Slf4j
public class StudyController {
    private final UserRepository userRepository;
    private final StudyService studyService;
    private final JoinService joinService;
    private final StudyQuestionService studyQuestionService;
    private final StudyMemberService studyMemberService;
    private final StudyFeedService studyFeedService;
    private final StudyWorkService studyWorkService;


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
        List<StudyQuestionDTO> questions = studyQuestionService.getInfo(studyId);
        List<Long> ids = study.getStudySupporterDTOS().stream().map(i->i.getUser().getUserId()).collect(Collectors.toList());

        List<Long> memberIds = study.getStudyMemberList().stream().map(i->i.getUserDTO().getUserId()).collect(Collectors.toList());
        memberIds.add(study.getUserId());
        model.addAttribute("memberList",memberIds);
        ids.add(study.getUserId());
        model.addAttribute("supportList",ids);
        model.addAttribute("questions",questions);
        model.addAttribute("writer",user);
        model.addAttribute("study",study);
        model.addAttribute("minusDay",studyService.minusDay(study));

        return "app/study/detail-study";
    }

    @GetMapping("/question/{studyId}")
    public String question(@PathVariable Long studyId,Model model, HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        Long userId = (Long)session.getAttribute("loginUser");
        StudyDTO study = studyService.getStudyDTO(studyId);
        UserDTO user = studyService.getInfo(study.getUserId());
        List<StudyQuestionDTO> questions = studyQuestionService.getInfo(studyId);
        List<Long> memberIds = study.getStudyMemberList().stream().map(i->i.getUserDTO().getUserId()).collect(Collectors.toList());
        memberIds.add(study.getUserId());
        model.addAttribute("memberList",memberIds);
        model.addAttribute("questions",questions);
        model.addAttribute("writer",user);
        model.addAttribute("study",study);
        model.addAttribute("minusDay",studyService.minusDay(study));
        model.addAttribute("nowUser",studyService.nowWriter(userId));



        return "app/study/questionStudy";
    }

    @GetMapping("/management/{studyId}")
    public String management(@PathVariable Long studyId,Model model, HttpServletRequest request){
        StudyDTO study = studyService.getStudyDTO(studyId);
        UserDTO user = studyService.getInfo(study.getUserId());
        List<StudyQuestionDTO> questions = studyQuestionService.getInfo(studyId);
        model.addAttribute("supporter",studyMemberService.showWaitList());
        List<Long> memberIds = study.getStudyMemberList().stream().map(i->i.getUserDTO().getUserId()).collect(Collectors.toList());
        memberIds.add(study.getUserId());
        model.addAttribute("memberList",memberIds);
        model.addAttribute("questions",questions);
        model.addAttribute("writer",user);
        model.addAttribute("study",study);
        model.addAttribute("minusDay",studyService.minusDay(study));
        return "app/study/studyManagement";
    }

    @GetMapping("/feed/{studyId}")
    public String feedDetail(@PathVariable Long studyId, Model model, HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        Long userId = (Long)session.getAttribute("loginUser");
        StudyDTO study = studyService.getStudyDTO(studyId);
        UserDTO user = studyService.getInfo(study.getUserId());
        List<StudyQuestionDTO> questions = studyQuestionService.getInfo(studyId);
        List<Long> ids = study.getStudySupporterDTOS().stream().map(i->i.getUser().getUserId()).collect(Collectors.toList());

        List<Long> memberIds = study.getStudyMemberList().stream().map(i->i.getUserDTO().getUserId()).collect(Collectors.toList());
        memberIds.add(study.getUserId());
        model.addAttribute("memberList",memberIds);
        ids.add(study.getUserId());
        model.addAttribute("supportList",ids);
        model.addAttribute("questions",questions);
        model.addAttribute("writer",user);
        model.addAttribute("study",study);
        model.addAttribute("minusDay",studyService.minusDay(study));
        model.addAttribute("nowUser",studyService.nowWriter(userId));
        return "app/study/feed-study";
    }

    @GetMapping("/work/{studyId}")
    public String workDetail(@PathVariable Long studyId, Model model){

        StudyDTO study = studyService.getStudyDTO(studyId);
        UserDTO user = studyService.getInfo(study.getUserId());
        List<StudyQuestionDTO> questions = studyQuestionService.getInfo(studyId);
        List<Long> ids = study.getStudySupporterDTOS().stream().map(i->i.getUser().getUserId()).collect(Collectors.toList());


        List<Long> memberIds = study.getStudyMemberList().stream().map(i->i.getUserDTO().getUserId()).collect(Collectors.toList());
        memberIds.add(study.getUserId());
        model.addAttribute("memberList",memberIds);
        ids.add(study.getUserId());
        model.addAttribute("supportList",ids);
        model.addAttribute("questions",questions);
        model.addAttribute("writer",user);
        model.addAttribute("study",study);
        model.addAttribute("minusDay",studyService.minusDay(study));
        model.addAttribute("proceed",studyWorkService.showProceeding(studyId));
        model.addAttribute("finish",studyWorkService.showFinish(studyId));
        return "app/study/workStudy";
    }




    @PostMapping("/feed/write")
    public RedirectView feedPost(StudyFeedDTO studyFeedDTO){
        studyFeedService.post(studyFeedDTO);
        return new RedirectView("/study/feed/"+studyFeedDTO.getStudy().getStudyId());
    }


    @PostMapping("/question/write")
    public RedirectView questionPost(StudyQuestionDTO studyQuestionDTO, Long userId,Long studyId){
        studyQuestionService.post(studyQuestionDTO,userId,studyId);
        return new RedirectView("/study/question/"+studyId);
    }

    @PostMapping("/delete")
    public RedirectView delete(Long studyId){
        studyService.remove(studyId);
        return new RedirectView("/study/list");
    }

    @PostMapping("/update")
    public RedirectView update(Long studyId, StudyDTO studyDTO, String date_input){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(date_input, formatter);
        StudyDTO dto = studyDTO;
        dto.setStudyId(studyId);
        dto.setStudyEndDate(date);
        studyService.update(dto);
        return new RedirectView("/study/management/"+studyId);
    }

    @PostMapping("/support-update")
    public RedirectView supportUpdate(Long studyId, int studySupportTotal){
        studyService.supportUpdate(studyId,studySupportTotal);
        return new RedirectView("/study/management/"+studyId);
    }

    @PostMapping("/support-end")
    public RedirectView supportEnd(Long studyId){
        studyService.supportEnd(studyId);
        return  new RedirectView("/study/management/"+studyId);
    }
}
