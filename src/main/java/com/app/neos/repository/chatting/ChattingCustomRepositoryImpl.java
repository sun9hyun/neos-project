package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.chatting.QChattingDTO;
import com.app.neos.entity.chatting.QChatting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ChattingCustomRepositoryImpl implements ChattingCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    ChattingDTO chattingDTO = new ChattingDTO();

//    //    채팅방에서 상대방 아이디에 맞는 아이디 찾기 (1개)
//    @Override
//    public ChattingDTO findByChatting(Long receiverId) {
//        return jpaQueryFactory.select(new QChattingDTO(
//                QChatting.chatting.chattingId,
//                QChatting.chatting.myId,
//                QChatting.chatting.receiverId,
//                QChatting.chatting.c,
//                QChatting.chatting.chattingId
//        )).from(QChatting.chatting)
//                .where(QChatting.chatting.chattingId.eq())
//                .fetchOne();
//    }


    @Override
    public Long findChattingAndUserId(Long chattingId) {
        return jpaQueryFactory.select(QChatting.chatting.chattingId)
                .from(QChatting.chatting)
                .where(QChatting.chatting.chattingId.eq(chattingId)).fetchOne();
    }
}
