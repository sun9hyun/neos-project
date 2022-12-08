package com.app.neos.service.fix;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.ChatContentCustomRepository;
import com.app.neos.repository.chatting.ChatRoomCustomRepository;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FixService {
    private final ChattingRepository chattingRepository;
    private final ChatRoomCustomRepository chatRoomCustomRepository;
    private final ChattingContentRepository chattingContentRepository;
    private final ChatContentCustomRepository chatContentCustomRepository;

//    채팅방 전체 조회
//    public List<ChattingDTO> findChatRoom(){
//        return chatRoomCustomRepository.findAllRoom();
//    }

//    채팅방 내용 조회
    public List<ChattingRoomDTO> findContent(Long loginUser){
        return chatContentCustomRepository.findAllContent(loginUser);
    }

//    채팅방 마지막 내용 조회
    public List<ChattingContentDTO> findLastContent(Long loginUser){
        return chatContentCustomRepository.findContent(loginUser);
    }

//    상대방 아이디에 따른 채팅방 내용 조회
    public List<ChattingContentDTO> findByIdTest(Long receiver){
        return chatContentCustomRepository.findById(receiver);
    }

    public List<Long> findReceiverId(Long receiver){
       return chatContentCustomRepository.findById(receiver).stream().map(i->i.getReceiver().getUserId()).collect(Collectors.toList());
    }
}
