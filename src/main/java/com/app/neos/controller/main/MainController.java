package com.app.neos.controller.main;

import com.app.neos.aspect.annotation.QuestionReplyAlarm;
import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.fix.FixService;
import com.app.neos.service.join.JoinService;
import com.app.neos.service.main.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/*")
public class MainController {

    private final FixService fixService;
    private final MainService mainService;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final JoinService joinService;


    @GetMapping("/main")
    public String login( HttpServletRequest  request, Model model){
        HttpSession session = request.getSession();
        Long loginUser = (Long) session.getAttribute("loginUser");
//        session.setAttribute("receiver",receiver);
//
//        System.out.println("-----------------------" + receiver);
        if(loginUser!=null) {
            List<ChattingRoomDTO> chattingContentDTOS = fixService.findContent(loginUser);
            model.addAttribute("contents", chattingContentDTOS);

        }
//       유저 정보
        Pageable pageable = PageRequest.of(0, 4);

        List<UserDTO> userDTOS = mainService.findUserPage();
                model.addAttribute("users",userDTOS);

//      스터디 정보
        List<StudyDTO> studyDTOS = mainService.findStudyPage();
                model.addAttribute("study" ,studyDTOS);

//      상점 정보
        Slice<StoreDTO> storeDTOS = mainService.findStorePage(pageable);
//        List<StoreDTO> storeDTOS = mainService.findStorePage();
        model.addAttribute("stores",storeDTOS);

        storeDTOS.stream().map(StoreDTO::toString).forEach(log::info);

//      커뮤니티 정보
        Slice<CommunityDTO> communityDTOS = mainService.findCommunityPage(pageable);
        model.addAttribute("community" , communityDTOS);


//      유저 상세보기
        model.addAttribute("collegeCityList",joinService.getCollegeCityList());


//      유저 아이디 넘기기
        model.addAttribute("userId", loginUser == null ? 0 : loginUser);



        return "app/main/main";
    }

    @GetMapping("/login-main")
    public String loginMain(){
        return "app/main/loginMain";
    }
}


