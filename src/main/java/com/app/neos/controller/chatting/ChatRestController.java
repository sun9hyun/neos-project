package com.app.neos.controller.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.fix.FixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/*")
public class ChatRestController {
    private final FixService fixService;
    private final ChattingContentRepository chattingContentRepository;
    private final UserRepository userRepository;


    //    채팅방 내용
    @GetMapping("/chatContent/{receiverRoomId}")
    public List<ChattingContentDTO> content(@PathVariable("receiverRoomId")Long chattingRoomId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long loginUser = (Long) session.getAttribute("loginUser");
        session.setAttribute("chattingRoomId", chattingRoomId);

        List<ChattingContentDTO> contents = fixService.findByIdTest(chattingRoomId);

        if (!contents.isEmpty()) {
            for (ChattingContentDTO chatDTO : contents) {
                if (!chatDTO.getWriter().equals(chatDTO.getWriter().getUserId())) {
                    fixService.readChange(chatDTO.getWriter().getUserId(), chatDTO.getWriter().getUserId());
                }


            }
            System.out.println("#############################조회" + contents);
        }
        return contents;
    }

//    @PostMapping("/chatContent/{receiverRoomId}")
//    public Long findId(@PathVariable("receiverRoomId")Long id,ChattingContentDTO chattingContentDTO,HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.setAttribute("chattingRoomId",id);
//        fixService.findByReceiverId(id);
//        return id;
//    }

    //    채팅방 내용 저장
    @PostMapping(value = "/chattingOk", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String save(@RequestBody ChattingContentDTO chattingContentDTO, HttpServletRequest request) {
        fixService.saveChatting(chattingContentDTO);
        System.out.println("#############################컨트롤러저장" + chattingContentDTO);
        return "success";
    }

//    채팅방 만들기
    @PostMapping(value = "/saveOk", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String roomSave(@RequestBody ChattingRoomDTO chattingRoomDTO){
        fixService.createChattingRoom(chattingRoomDTO);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$채팅방 저장임 " + chattingRoomDTO);
        return "saveSuccess";
    }

////    채팅방 나가기
//    @DeleteMapping("/chattingDelete/{receiverRoomId}")
//    public String delete(@PathVariable("receiverRoomId")Long chattingRoomId, HttpServletRequest request){
//        HttpSession session = request.getSession();
//        session.setAttribute("chattingRoomId",chattingRoomId);
//        fixService.deleteRoom(chattingRoomId);
//        log.info(chattingRoomId.toString());
//        log.info("삭제 컨트롤러 $$$$$$$$$$$$$$$44");
//        return "delete success";
//    }



}

