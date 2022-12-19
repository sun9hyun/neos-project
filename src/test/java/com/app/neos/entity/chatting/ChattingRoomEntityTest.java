package com.app.neos.entity.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRoomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.chatting.ChatType;
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
public class ChattingRoomEntityTest {
    @Autowired
    ChattingContentRepository chattingContentRepository;
    @Autowired
    ChattingRoomRepository chattingRoomRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest() {
        ChattingRoomDTO chattingRoomDTO = new ChattingRoomDTO();

        chattingRoomDTO.setMyRoom(userRepository.findById(182l).get());
        chattingRoomDTO.setReceiverRoom(userRepository.findById(18l).get());
        ChattingRoom chattingRoom = chattingRoomDTO.toEntity();
        chattingRoomRepository.save(chattingRoom);

    }

//    @Test
//    public void updateTest(){
//        ChattingRoom chattingRoom1 = chattingRoomRepository.findById(34l).get();
//        ChattingRoomDTO chattingRoomDTO = new ChattingRoomDTO();
//        chattingRoomDTO.setChattingContentRoom("또 수정됬음");
//        chattingRoom1.update(chattingRoomDTO);
//    }
}
