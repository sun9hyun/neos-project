package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.service.community.CommunityReplyService;
import com.app.neos.service.community.CommunityService;
import com.app.neos.service.counseling.CounselingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityController {
    private final CommunityService communityService;
    private final CommunityReplyService communityReplyService;
    private final CounselingService counselingService;

//    자유게시판 목록
    @GetMapping("/community")
    public String community(CommunityDTO communityDTO, CommunityReplyDTO communityReplyDTO, Model model){
        List<CommunityDTO> communityDTOS = communityService.findAll();
        List<UserDTO> userDTOS = communityService.findNeosUser();
        List<StudyDTO> studyDTOS = communityService.findStudy();

        model.addAttribute("communitys", communityDTOS);
        model.addAttribute("neosUsers", userDTOS);
        model.addAttribute("studys", studyDTOS);
        return "app/community/freeboard";
    }
//    자유게시판 글잓성
    @PostMapping("/community")
    public RedirectView communityOk(CommunityDTO communityDTO){
        communityService.saveCommunity(communityDTO);

        return new RedirectView("community");
    }


//    고민게시판 목록
    @GetMapping("/counseling")
    public String counseling(CounselingDTO counselingDTO, Model model){
        List<CounselingDTO> counselingDTOS = counselingService.findAll();
        List<UserDTO> userDTOS = communityService.findNeosUser();

        model.addAttribute("counselings", counselingDTOS);
        model.addAttribute("neosUsers", userDTOS);
        return "app/community/counselingboard";
    }
//    고민게시판 글작성
    @PostMapping("/counseling")
    public RedirectView counselingOk(CounselingDTO counselingDTO){
        counselingService.saveCounseling(counselingDTO);

        return new RedirectView("counseling");
    }









//    test
    @GetMapping("header")
    public String header(){
        return "app/community/headerghost";
    }

    @GetMapping("footer")
    public String footer(){
        return "app/fix/footer";
    }
}
