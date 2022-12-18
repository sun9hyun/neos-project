package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.neos.entity.chatting.QChattingContent.*;
import static com.app.neos.entity.chatting.QChattingRoom.chattingRoom;

@Repository
@RequiredArgsConstructor
public class ChatContentCustomRepositoryImpl implements ChatContentCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


//    로그인한 세션아이디가 채팅방에 있는 아이디랑 같은 채팅방 목록

    @Override
    public List<ChattingRoomDTO> findAllContent(Long loginUser) {
        return jpaQueryFactory.select(new QChattingRoomDTO(
                chattingRoom.chattingRoomId,
                chattingRoom.myRoom,
                chattingRoom.receiverRoom,
                chattingRoom.chatDate
        )).from(chattingRoom)
                .where(chattingRoom.myRoom.userId.eq(loginUser)
                        .or(chattingRoom.receiverRoom.userId.eq(loginUser)))
                .orderBy(chattingRoom.chatDate.desc())
                .fetch();
    }


    // 채팅 내용 조회
    @Override
    public List<ChattingContentDTO> findById(Long chattingRoomId) {
        return jpaQueryFactory.select(new QChattingContentDTO(
                chattingContent1.chattingContentId,
                chattingContent1.chattingContent,
                chattingContent1.writer,
                chattingContent1.chattingRoom,
                chattingContent1.chatDate,
                chattingContent1.messageType
        )).from(chattingContent1)
                .where(chattingContent1.chattingRoom.chattingRoomId.eq(chattingRoomId))
                .orderBy(chattingContent1.chatDate.asc())
                .fetch();
    }

//    채

    @Override
    public Long findByIdOne(Long chattingRoomId) {
        return jpaQueryFactory.select(
                chattingRoom.chattingRoomId
        ).from(chattingRoom)
                .where(chattingRoom.chattingRoomId.eq(chattingRoomId))
                .fetchOne();

    }
//
//}
//
}