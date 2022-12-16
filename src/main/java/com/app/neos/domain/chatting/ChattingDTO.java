package com.app.neos.domain.chatting;


import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;



import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Component
@Data
@Slf4j
@NoArgsConstructor
public class ChattingDTO {

    private Long chattingId;
    private User myId;
    private User receiverId;
    private ChattingContent chattingContent;
    private ChatType chatType;
    private LocalDateTime createdDate;
    private static Map<Long, WebSocketSession> sessions = new HashMap<>();

    public Chatting toEntity(){
        return Chatting.builder()
                .myId(myId)
                .receiverId(receiverId)
                .build();
    }

    @QueryProjection
    public ChattingDTO(Long chattingId, User myId, User receiverId,ChattingContent chattingContent, LocalDateTime createdDate) {
        this.chattingId = chattingId;
        this.myId = myId;
        this.receiverId = receiverId;
        this.createdDate = createdDate;

    }
    @QueryProjection
    public ChattingDTO(Long chattingId) {
        this.chattingId = chattingId;

    }
    @QueryProjection
    public ChattingDTO(Long chattingId, User myId, User receiverId,  Map<Long, WebSocketSession> sessions) {
        this.chattingId = chattingId;
        this.myId = myId;
        this.receiverId = receiverId;
        this.sessions = sessions;
    }

    @QueryProjection
    public ChattingDTO(Long chattingId, User myId, User receiverId,ChatType chatType,LocalDateTime createdDate) {
        this.chattingId = chattingId;
        this.myId = myId;
        this.receiverId = receiverId;
        this.chatType = chatType;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public ChattingDTO(Long chattingId, User myId, User receiverId,ChattingContent chattingContent,ChatType chatType,LocalDateTime createdDate) {
        this.chattingId = chattingId;
        this.myId = myId;
        this.receiverId = receiverId;
        this.chattingContent = chattingContent;
        this.chatType = chatType;
        this.createdDate = createdDate;
    }

    public ChattingDTO create(String name){
        ChattingDTO chatRoom = new ChattingDTO();
        chatRoom.chattingId = chattingId;
        chatRoom.myId = myId;
        chatRoom.receiverId = receiverId;
        chatRoom.createdDate = createdDate;
        chatRoom.chattingContent = chattingContent;
        return chatRoom;
    }

//    /*--웹소켓 핸들러 메세지 설정--- */
//
//    public void handleMessage(WebSocketSession session, ChattingContentDTO chattingContentDTO,
//                              ObjectMapper objectMapper) throws IOException {
//        ChattingDTO chattingDTO = new ChattingDTO();
//        log.info("********handleMessage");
//        log.info(session.toString());
////        log.info(String.valueOf(session.getAttributes().get("receiver").toString()));
//        log.info(chattingContentDTO.toString());
//        log.info(chattingDTO.toString());
////        if(session.getAttributes().get("receiver") == chattingContentDTO.getReceiver().getUserId()){
////            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver().getUserNickName() + "님이 입장하셨습니다.");
////            sessions.put(chattingContentDTO.getReceiver().getUserId(), session);
////        }else {
////            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver().getUserNickName() + " : " + chattingContentDTO.getChattingContent());
////        }
//
////        sessions.put(chattingContentDTO.getReceiver(),session);
////        if(session.getAttributes().get("receiver") == chattingContentDTO.getReceiver().getUserId()){
//            send(chattingContentDTO,objectMapper);
//    }
//
//// 확성기 없애기
//    private void send(ChattingContentDTO chattingContentDTO, ObjectMapper objectMapper) throws IOException {
//        log.info("=========send");
//        log.info(chattingContentDTO.toString());
//        TextMessage textMessage = new TextMessage(objectMapper.
//                writeValueAsString(chattingContentDTO.getChattingContent()));
//        log.info(sessions.toString());
//        for(WebSocketSession sess : sessions.values()){
////            if(sess.getAttributes().get("receiver") == chattingContentDTO.getReceiver())
//            {
//                sess.sendMessage(textMessage);
//            }
//        }
//    }

}
