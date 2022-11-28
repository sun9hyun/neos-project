package com.app.neos.entity.chatting;

import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.ChattingRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.neos.entity.user.QUser.user;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChattingEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChattingRepository chattingRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){
        ChattingDTO chattingDTO = new ChattingDTO();
        Chatting chatting = Chatting.builder().myId(userRepository.findById(2l).get()).receiverId(userRepository.findById(4l).get()).build();
        chatting.builder();
        chattingRepository.save(chatting);

    }

    @Test
    public void deleteTest(){
        chattingRepository.deleteById(5l);
    }

    @Test
    public void findAllTest(){
        chattingRepository.findAll().stream().map(Chatting::toString).forEach(log::info);
    }


}
