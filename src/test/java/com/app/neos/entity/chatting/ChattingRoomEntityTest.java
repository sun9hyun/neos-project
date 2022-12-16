package com.app.neos.entity.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRepository;
import com.app.neos.repository.chatting.ChattingRoomRepository;
import com.app.neos.type.chatting.ChatType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
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
    ChattingRepository chattingRepository;

    @Test
    public void saveTest() {
        ChattingRoomDTO chattingRoomDTO = new ChattingRoomDTO();
        ChattingContentDTO chattingContentDTO = new ChattingContentDTO();
        chattingRoomDTO.setChattingContentRoom(chattingContentRepository.findById(13l).get().getChattingContent());
        chattingRoomDTO.setChatType(ChatType.CHAT);
        chattingRoomDTO.setMyRoom(chattingContentRepository.findById(13l).get().getMy());
        chattingRoomDTO.setReceiverRoom(chattingContentRepository.findById(13l).get().getReceiver());
        chattingRoomDTO.setChattingContentIdRoom(chattingContentRepository.findById(13l).get());

        ChattingRoom chattingRoom = chattingRoomDTO.toEntity();

        chattingRoom.changeMyRoom(chattingRoomDTO.getMyRoom());
        chattingRoom.changeReceiverRoom(chattingRoomDTO.getReceiverRoom());
        chattingRoom.changeChattingContentId(chattingRoomDTO.getChattingContentIdRoom());


        chattingRoomRepository.save(chattingRoom);

    }

    @Test
    public void updateTest(){
        ChattingRoom chattingRoom1 = chattingRoomRepository.findById(34l).get();
        ChattingRoomDTO chattingRoomDTO = new ChattingRoomDTO();
        chattingRoomDTO.setChattingContentRoom("또 수정됬음");
        chattingRoom1.update(chattingRoomDTO);
    }
}
