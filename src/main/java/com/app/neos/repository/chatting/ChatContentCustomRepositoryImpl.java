package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.domain.chatting.QChattingContentDTO;
import com.app.neos.domain.chatting.QChattingRoomDTO;
import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.QChattingContent;
import com.app.neos.entity.chatting.QChattingRoom;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.app.neos.entity.chatting.QChattingContent.*;
import static com.app.neos.entity.chatting.QChattingRoom.chattingRoom;

@Repository
@RequiredArgsConstructor
public class ChatContentCustomRepositoryImpl implements ChatContentCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChattingRoomDTO> findAllContent(Long loginUser){

        return jpaQueryFactory.select(new QChattingRoomDTO(
                chattingRoom.chattingRoomId,
                chattingRoom.chattingContentRoom,
                chattingRoom.myRoom,
                chattingRoom.receiverRoom,
                chattingRoom.chattingContentIdRoom,
                chattingRoom.chatType,
                chattingRoom.updatedDate
               )).from(chattingRoom)
                .where( chattingRoom.myRoom.userId.eq(loginUser))
                .orderBy( chattingRoom.updatedDate.desc())
                .fetch();
    }

    @Override
    public List<ChattingContentDTO> findContent(Long loginUser){
        return jpaQueryFactory.select(new QChattingContentDTO(
                chattingContent1.chattingContent,
                chattingContent1.createdDate
                )).from(chattingContent1)
                .where(chattingContent1.my.userId.eq(loginUser))
                .orderBy(chattingContent1.createdDate.desc())
                .fetch();
    }

    @Override
    public List<ChattingContentDTO> findById(Long receiver){

        return jpaQueryFactory.select(new QChattingContentDTO(
                chattingContent1.chattingContentId,
                chattingContent1.chattingContent,
                chattingContent1.my,
                chattingContent1.receiver,
                chattingContent1.chatting,
                chattingContent1.createdDate
        )).from(chattingContent1)
                .where(chattingContent1.receiver.userId.eq(receiver))
                .fetch();

    }

}
