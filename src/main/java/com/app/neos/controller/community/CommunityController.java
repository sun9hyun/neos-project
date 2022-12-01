package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.service.community.CommunutyService;
import com.app.neos.service.counseling.CounselingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityController {
    private final CommunutyService communutyService;
    private final CounselingService counselingService;

//    자유게시판
    @GetMapping("/community")
    public String community(CommunityDTO communityDTO){
        return "app/community/freeboard";
    }

    @PostMapping("/community")
    public RedirectView communityOk(CommunityDTO communityDTO){
        Community community = communityDTO.toEntity();
        communutyService.saveCommunity(community);

        return new RedirectView("community");
    }


//    고민게시판
    @GetMapping("/counseling")
    public String counseling(CounselingDTO counselingDTO){
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
