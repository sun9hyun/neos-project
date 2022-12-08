package com.app.neos.controller.main;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.service.fix.FixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/main/*")
public class MainController {

    private final FixService fixService;

    @GetMapping("/main")
    public String login(HttpServletRequest  request,Model model){
        HttpSession session = request.getSession();
        Long loginUser = (Long) session.getAttribute("loginUser");
        System.out.println("-----------------------" + loginUser);
        if(loginUser!=null) {
            List<ChattingRoomDTO> chattingContentDTOS = fixService.findContent(loginUser);
            model.addAttribute("contents", chattingContentDTOS);

        }

        return "app/main/main";
    }

    @GetMapping("/login-main")
    public String loginMain(){
        return "app/main/loginMain";
    }
}
