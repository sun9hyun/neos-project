package com.app.neos.entity.chatting;


import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRoomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.chatting.ChatType;
import com.app.neos.type.chatting.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChattingContentEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChattingRoomRepository chattingRoomRepository;
    @Autowired
    ChattingContentRepository chattingContentRepository;


    @Test
    public void saveTest() {


        ChattingContentDTO chattingContentDTO = new ChattingContentDTO();
        ChattingRoomDTO chattingRoomDTO = new ChattingRoomDTO();

        chattingContentDTO.setChattingContent("?? ");
        chattingContentDTO.setMessageType(MessageType.UNREAD);
        chattingContentDTO.setWriter(chattingRoomRepository.findById(184l).get().getMyRoom()); // 보낸사람
        chattingContentDTO.setChattingRoom(chattingRoomRepository.findById(184l).get());
        ChattingContent chattingContent = chattingContentDTO.toEntity();

        chattingContent.changeChattingRoom(chattingContentDTO.getChattingRoom());
        chattingContentRepository.save(chattingContent);

        //채팅방
    }
//    @Test
//    public void deleteTest(){
//        chattingContentRepository.deleteById(7l);
//    }
//
//    @Test
//    public void findAllTest(){
//        chattingContentRepository.findAll().stream().map(ChattingContent::toString).forEach(log::info);
//    }

}