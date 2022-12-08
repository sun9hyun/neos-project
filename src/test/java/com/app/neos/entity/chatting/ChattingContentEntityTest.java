package com.app.neos.entity.chatting;


import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRepository;
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
public class ChattingContentEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChattingRepository chattingRepository;
    @Autowired
    ChattingContentRepository chattingContentRepository;

    @Test
    public void saveTest(){
        ChattingContentDTO chattingContentDTO = new ChattingContentDTO();

        chattingContentDTO.setChatting(chattingRepository.findById(7l).get());
        chattingContentDTO.setMy(chattingRepository.findById(7l).get().getMyId());
        chattingContentDTO.setReceiver(chattingRepository.findById(7l).get().getReceiverId());
        chattingContentDTO.setChatType(ChatType.ENTER);
        chattingContentDTO.setChattingContent("테스트임당");

        ChattingContent chattingContent = chattingContentDTO.toEntity();

        chattingContent.changeMy(chattingContentDTO.getMy());
        chattingContent.changeReceiver(chattingContentDTO.getReceiver());
        chattingContent.changeChatting(chattingContentDTO.getChatting());

        chattingContentRepository.save(chattingContent);
    }

    @Test
    public void deleteTest(){
        chattingContentRepository.deleteById(7l);
    }

    @Test
    public void findAllTest(){
        chattingContentRepository.findAll().stream().map(ChattingContent::toString).forEach(log::info);
    }
}
