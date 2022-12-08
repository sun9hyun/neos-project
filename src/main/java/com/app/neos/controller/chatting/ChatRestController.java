package com.app.neos.controller.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.entity.user.User;
import com.app.neos.service.fix.FixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/*")
public class ChatRestController {
    private final FixService fixService;

    @GetMapping("/chatContent/{receiverRoom}")
    public List<ChattingContentDTO> content(@PathVariable("receiverRoom") Long receiver) {
        List<ChattingContentDTO> contents = fixService.findByIdTest(receiver);
        System.out.println("******************************"+contents);
        return contents;
    }

    @GetMapping("/chatReceiver/{receiverRoom}")
    public List<Long> contenttest(@PathVariable("receiverRoom") Long receiver){
        return fixService.findReceiverId(receiver);
    }
}

