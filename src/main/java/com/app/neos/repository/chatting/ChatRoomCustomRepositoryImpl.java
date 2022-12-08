package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.*;
import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.QChatting;
import com.app.neos.entity.chatting.QChattingRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.chatting.QChattingContent.chattingContent1;

@Repository
@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

//    @Override
//    public List<ChattingRoomDTO> findAllRoom(Long loginUser){
//        return jpaQueryFactory.select(new QChattingRoomDTO(
//                QChattingRoom.chattingRoom.chattingRoomId,
//                QChattingRoom.chattingRoom.chattingContentRoom,
//                QChattingRoom.chattingRoom.myRoom,
//                QChattingRoom.chattingRoom.receiverRoom,
//                QChattingRoom.chattingRoom.chattingContentIdRoom,
//                QChattingRoom.chattingRoom.chatType,
//                QChattingRoom.chattingRoom.updatedDate
//        )).from(chattingRoom)
//                .where(chattingContent1.my.userId.eq(loginUser))
//                .orderBy(chattingContent1.createdDate.desc())
//                .fetch();
//    }

}
