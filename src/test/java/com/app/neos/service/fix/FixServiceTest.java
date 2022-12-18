package com.app.neos.service.fix;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.QChattingContentDTO;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.repository.chatting.ChatContentCustomRepository;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRoomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.chatting.ChatType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.neos.entity.chatting.QChattingContent.chattingContent1;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FixServiceTest {
    @Autowired
    FixService fixService;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    ChattingContentRepository chattingContentRepository;
    @Autowired
    ChattingRoomRepository chattingRoomRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChattingContentDTO chattingContentDTO;
    @Autowired
    ChatContentCustomRepository chatContentCustomRepository;


    //    채팅방 내용 조회


    @Test
    public void test() {

        log.info(fixService.findByIdTest(8l).toString());
    }
}
//
//
//        @Test
//        public void test2(){
//        Long receiver = fixService.findByIdOneTest(4l).getReceiver().getUserId();
//                ChattingContentDTO chattingContentDTO = jpaQueryFactory.select(new QChattingContentDTO(
//                        chattingContent1.receiver
//                )).distinct().from(chattingContent1)
//                        .where(chattingContent1.receiver.userId.eq(receiver))
//                        .fetchOne();
//
//            }
//
//        @Test
//    public void saveTest(){
//        ChattingContentDTO chattingContentDTO = new ChattingContentDTO();
//            chattingContentDTO.setChatting(chattingRepository.findById(6L).get());
//            chattingContentDTO.setMy(chattingRepository.findById(6L).get().getMyId());
//            chattingContentDTO.setReceiver(chattingRepository.findById(6L).get().getReceiverId());
//            chattingContentDTO.setChatType(ChatType.ENTER);
//            chattingContentDTO.setChattingContent("테스트! ! !");
//
//            ChattingContent chattingContent = chattingContentDTO.toEntity();
//
//            chattingContent.changeMy(chattingContentDTO.getMy());
//            chattingContent.changeReceiver(chattingContentDTO.getReceiver());
//            chattingContent.changeChatting(chattingContentDTO.getChatting());
//
//            fixService.saveChatting(chattingContentDTO);
//        }
//    @Test
//    public void update( ){
//        chattingContentDTO.setChattingId(19l);
//        chattingContentDTO.setMyId(1l);
//        chattingContentDTO.setReceiverId(33l);
//        fixService.updateChattingIdContent(chattingContentDTO.getMyId(),chattingContentDTO.getReceiverId(),chattingContentDTO.getChattingId());
//
//    }
//
//
//
//}
//
//
//
//
//
