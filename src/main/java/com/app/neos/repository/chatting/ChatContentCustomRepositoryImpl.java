package com.app.neos.repository.chatting;

import com.app.neos.domain.chatting.*;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.QChatting;
import com.app.neos.entity.chatting.QChattingContent;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.chatting.QChattingContent.*;
import static com.app.neos.entity.chatting.QChattingRoom.chattingRoom;

@Repository
@RequiredArgsConstructor
public class ChatContentCustomRepositoryImpl implements ChatContentCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


//세션 아이디랑 내아이디 같은 채팅룸(목록) 내용 전체

    @Override
    public List<ChattingRoomDTO> findAllContent(Long loginUser){
        return jpaQueryFactory.select(new QChattingRoomDTO(
                chattingRoom.chattingRoomId,
                chattingRoom.chattingContentRoom,
                chattingRoom.myRoom,
                chattingRoom.receiverRoom,
                chattingRoom.chattingContentIdRoom,
                chattingRoom.updatedDate
               )).from(chattingRoom)
                .where(chattingRoom.myRoom.userId.eq(loginUser))
                .orderBy( chattingRoom.updatedDate.desc())
                .fetch();
    }

    @Override
    public List<ChattingRoomDTO> findByReceiverId(Long receiverId){
        return jpaQueryFactory.select(new QChattingRoomDTO(
                chattingRoom.chattingRoomId,
                chattingRoom.chattingContentRoom,
                chattingRoom.myRoom,
                chattingRoom.receiverRoom,
                chattingRoom.chattingContentIdRoom,
                chattingRoom.updatedDate
        )).from(chattingRoom)
                .where(chattingRoom.myRoom.userId.eq(receiverId))
                .fetch();
    }


//    @Override
//    public List<ChattingDTO> findAllContent(Long loginUSer){
//        return jpaQueryFactory.select(new QChattingDTO(
//                QChatting.chatting.chattingId,
//                QChatting.chatting.myId,
//                QChatting.chatting.receiverId,
//                QChatting.chatting.chatType,
//                QChatting.chatting.createdDate
//               )).from(QChatting.chatting)
//                .where(QChatting.chatting.myId.userId.eq(loginUSer))
//                .orderBy(QChatting.chatting.createdDate.desc())
//                .fetch();
//    }





// 채팅방 내용
    @Override
    public List<ChattingContentDTO> findById(Long receiverId, Long myId){
        return jpaQueryFactory.select(new QChattingContentDTO(
                chattingContent1.chattingContentId,
                chattingContent1.chattingContent,
                chattingContent1.my,
                chattingContent1.receiver,
                chattingContent1.chatting,
                chattingContent1.createdDate
        )).from(chattingContent1)
                .where(chattingContent1.my.userId.eq(myId)
                        .and(chattingContent1.receiver.userId.eq(receiverId)))
                .orderBy(chattingContent1.createdDate.asc())
                .fetch();
    }

//    상대방 아이디 1개 찾기

    @Override
    public ChattingContentDTO findByIdOne(Long receiver){
        return jpaQueryFactory.select(new QChattingContentDTO(
                chattingContent1.my
        )).distinct().from(chattingContent1)
                .where(chattingContent1.receiver.userId.eq(receiver))
                .fetchOne();

    }

//    마지막 채팅 내용 가져오기

    @Override
    public List<ChattingContentDTO> findLastList(Long receiver){

        return jpaQueryFactory.select(new QChattingContentDTO(
                chattingContent1.chattingContentId,
                chattingContent1.chattingContent,
                chattingContent1.my,
                chattingContent1.receiver,
                chattingContent1.chatting,
                chattingContent1.createdDate
        )).from(chattingContent1)
                .where( chattingContent1.receiver.userId.eq(receiver))
                .orderBy( chattingContent1.createdDate.desc()).limit(1)
                .fetch();
    }


}
