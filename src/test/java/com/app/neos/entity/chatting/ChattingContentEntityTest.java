package com.app.neos.entity.chatting;


import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRepository;
import com.app.neos.repository.user.UserRepository;
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

        chattingContentDTO.setChatting(chattingRepository.findById(5l).get());
        chattingContentDTO.setMy(userRepository.findById(2l).get());
        chattingContentDTO.setReceiver(userRepository.findById(3l).get());
        chattingContentDTO.setChattingContent("안녕안녕");

        ChattingContent chattingContent = chattingContentDTO.toEntity();

        chattingContent.changeMy(chattingContentDTO.getMy());
        chattingContent.changeReceiver(chattingContentDTO.getReceiver());
        chattingContent.changeChatting(chattingContentDTO.getChatting());

        chattingContentRepository.save(chattingContent);

    }

    @Test
    public void deleteTest(){
        chattingContentRepository.deleteById(10l);
    }

    @Test
    public void findAllTest(){
        chattingContentRepository.findAll().stream().map(ChattingContent::toString).forEach(log::info);
    }
}
