package com.app.neos.controller.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.ChatRoomRepository;
import com.app.neos.service.fix.FixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomRepository chatRoomRepository;
    private final FixService fixService;
//
//    @PostMapping("/main")
//    public String chat() {
//        return "app/main/main";
//    }

}
