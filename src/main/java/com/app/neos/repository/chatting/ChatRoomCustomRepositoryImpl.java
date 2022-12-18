package com.app.neos.repository.chatting;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

//    채팅방 목록 업데이트
//    @Override
//    public void update(ChattingRoom chattingRoom) {
//
//    System.out.println("*********************" + chattingRoom + "*********************");
//
//    jpaQueryFactory.update(QChattingRoom.chattingRoom)
//            .set(QChattingRoom.chattingRoom.chattingContentRoom())
//}
}
