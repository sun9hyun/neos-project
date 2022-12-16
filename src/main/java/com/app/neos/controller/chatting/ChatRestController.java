package com.app.neos.controller.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.repository.chatting.ChatContentCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.fix.FixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/*")
public class ChatRestController {
    private final FixService fixService;
    private final UserRepository userRepository;
    private final ChatContentCustomRepository chatContentCustomRepository;
    private final ChattingContentDTO chattingContentDTO;


    //    채팅방 내용
    @GetMapping("/chatContent/{receiverRoomId}")
    public List<ChattingContentDTO> content(@PathVariable("receiverRoomId")Long receiverId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long myId = (Long) session.getAttribute("loginUser");
        session.setAttribute("receiver",receiverId);
        List<ChattingContentDTO> contents = fixService.findByIdTest(receiverId,myId);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + contents);
        return contents;
    }

    //    채팅방 내용
//    @PostMapping("/chatContent/{receiverRoomId}")
//    public List<ChattingRoomDTO> contents(@PathVariable("receiverRoomId")HttpServletRequest receiver,HttpServletRequest request) {
////        HttpSession session = request.getSession();
////        Long loginUser = (Long) session.getAttribute("loginUser");
////        session.setAttribute("receiver",receiver);
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + receiver);
//        List<ChattingRoomDTO> content = fixService.findByReceiverIdChatting(receiver);
//        return content;
//    }


//    채팅방 내용 저장
    @PostMapping(value = "/chattingOk", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String save(@RequestBody ChattingContentDTO chattingContentDTO, HttpServletRequest request) {
        fixService.saveChatting(chattingContentDTO);
        log.info("#############################" + chattingContentDTO);
        return "success";
    }

//    채팅방 나가기
    @DeleteMapping("/chattingDelete")
    public String delete(@RequestBody ChattingRoomDTO chattingRoomDTO){
        fixService.deleteRoom(chattingRoomDTO);
        log.info("@@@@@@@@@@@@@@@@@@@@@");
        return "delete success";
    }


}

