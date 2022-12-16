package com.app.neos.domain.chatting;

import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.app.neos.type.chatting.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@Slf4j
@NoArgsConstructor
public class ChattingContentDTO {

    private Long chattingContentId;
    private String chattingContent;
    private User my;
    private User receiver;
    private Chatting chatting;
    private MessageType messageType;
    private ChatType chatType;
    private LocalDateTime createdDate;
    private Long myId;
    private Long receiverId;
    private Long chattingId;
    private static Map<User, WebSocketSession> sessions = new HashMap<>();


    public ChattingContent toEntity() {
        return ChattingContent.builder()
                .chattingContent(chattingContent)
                .messageType(messageType)
                .my(my)
                .receiver(receiver)
                .build();
    }

    @QueryProjection
    public ChattingContentDTO(Long chattingContentId, String chattingContent, User my, User receiver, Chatting chatting, LocalDateTime createdDate) {
        this.chattingContentId = chattingContentId;
        this.chattingContent = chattingContent;
        this.my = my;
        this.receiver = receiver;
        this.chatting = chatting;
        this.createdDate = createdDate;
    }

//    @QueryProjection
//    public ChattingContentDTO(Long chattingContentId, String chattingContent, Long userId, Long chattingId,LocalDateTime createdDate) {
//        this.chattingContentId = chattingContentId;
//        this.chattingContent = chattingContent;
//        this.userId = userId;
//        this.chattingId = chattingId;
//        this.createdDate = createdDate;
//    }

    @QueryProjection
    public ChattingContentDTO(User receiver) {
        this.receiver = receiver;
    }
}
//    /*--웹소켓 핸들러 메세지 설정--- */
//
//    public void handleMessage(WebSocketSession session, ChattingContentDTO chattingContentDTO,
//                              ObjectMapper objectMapper) throws IOException {
//        ChattingDTO chattingDTO = new ChattingDTO();
//        log.info("********handleMessage");
//        log.info(session.toString());
//        log.info(String.valueOf(session.getAttributes().get("receiver").toString()));
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
//}
