package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.counseling.Counseling;
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
    private final CounselingService counselingService;

//    자유게시판
    @GetMapping("/communityList")
    public String community(CommunityDTO communityDTO, Model model){
        List<CommunityDTO> communityDTOS = communityService.findAll();

        model.addAttribute("communitys", communityDTOS);
        return "app/community/freeboard";
    }

    @PostMapping("/communityList")
    public RedirectView communityOk(CommunityDTO communityDTO){
        Community community = communityDTO.toEntity();
        communityService.saveCommunity(community);

        return new RedirectView("communityList");
    }






//    고민게시판
    @GetMapping("/counseling")
    public String counseling(CounselingDTO counselingDTO, Model model){
        List<CounselingDTO> counselingDTOS = counselingService.findAll();

        model.addAttribute("counselings", counselingDTOS);
        return "app/community/counselingboard";
    }

    @PostMapping("/counseling")
    public RedirectView counselingOk(CounselingDTO counselingDTO){
        Counseling counseling = counselingDTO.toEntity();
        counselingService.saveCounseling(counseling);

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
