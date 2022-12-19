package com.app.neos.domain.chatting;

import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Data
@NoArgsConstructor
public class ChattingRoomDTO {

    private Long chattingRoomId;
    @JsonIgnore
    private User myRoom;
    @JsonIgnore
    private User receiverRoom;
    private LocalDateTime chatDate;
    private Long receiverId;
    private Long listLookId;
    @JsonIgnore
    private Long myRoomId;
    @JsonIgnore
    private Long receiverRoomId;
    private static Map<Long, WebSocketSession> sessions = new HashMap<>();
    private ChattingRoomDTO chattingRoomDTO;

    public ChattingRoom toEntity() {
        return ChattingRoom.builder()
                .myRoom(myRoom)
                .receiverRoom(receiverRoom)
                .build();
    }

    @QueryProjection
    public ChattingRoomDTO(Long chattingRoomId, User myRoom, User receiverRoom, LocalDateTime chatDate) {
        this.chattingRoomId = chattingRoomId;
        this.myRoom = myRoom;
        this.receiverRoom = receiverRoom;
        this.chatDate = chatDate;
    }

    @QueryProjection
    public ChattingRoomDTO(Long chattingRoomId) {
        this.chattingRoomId = chattingRoomId;

    }

//    /*--웹소켓 핸들러 메세지 설정--- */
//    // receiverId로 세션 받던거 chattingRoomId로 받기 (채팅방 아이디 기준으로)
//
//    public void handleMessage(WebSocketSession session, ChattingContentDTO chattingContentDTO,
//                              ObjectMapper objectMapper) throws IOException {
//
//        log.info("********handleMessage");
//        log.info(String.valueOf(session.getAttributes().get("receiverRoomId")));
//        log.info(String.valueOf(chattingContentDTO.getChattingRoomId()));
//
//
////        if (session.getAttributes().get("receiver") == chattingContentDTO.getReceiver()) {
////            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver().getUserNickName() + "님이 입장하셨습니다.");
////
//        sessions.put(chattingContentDTO.getChattingRoomId(), session);
//        log.info(sessions.toString());
////        } else {
////            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver().getUserNickName() + " : " + chattingContentDTO.getChattingContent());
////        }
//
////        sessions.put(chattingRoomDTO.getReceiverRoom(), session);
////        if (session.getAttributes().get("receiver") == chattingContentDTO.getReceiver().getUserId()) {
//        send(chattingContentDTO, objectMapper);
////        }
//    }
//
//
//    // 확성기 없애기
//    private void send (ChattingContentDTO chattingContentDTO, ObjectMapper objectMapper) throws IOException {
//        log.info("=========send");
//        log.info(chattingContentDTO.toString());
//        TextMessage textMessage = new TextMessage(objectMapper.
//                writeValueAsString(chattingContentDTO.getChattingContent()));
//        for (WebSocketSession sess : sessions.values())
//            {
//                if (sess.getAttributes().get("receiverRoomId") == chattingContentDTO.getChattingRoomId())
//            sess.sendMessage(textMessage);
//        }
////        log.info(sessions.toString());
//    }
//}



    /*--웹소켓 핸들러 메세지 설정--- */

    public void handleMessage(WebSocketSession session, ChattingContentDTO chattingContentDTO,
                              ObjectMapper objectMapper) throws IOException {

        log.info("********handleMessage");
        log.info(String.valueOf(session.getAttributes().get("chattingRoomId")));
        log.info(chattingContentDTO.toString());
//        채팅방 세션 값과 chattingRoomId 값이 같다면
        if (session.getAttributes().get("chattingRoomId") == chattingContentDTO.getChattingRoomId()) {
//            세션값을 작성자 아이디에 저장
//
            sessions.put(chattingContentDTO.getWriterId(), session);
        }
        send(chattingContentDTO, objectMapper);
        log.info(sessions.toString());

//        sessions.put(chattingRoomDTO.getReceiverRoom(), session);
//        if (session.getAttributes().get("receiverRoomId") == chattingContentDTO.getChattingRoomId()) {
//        }
    }


//    채팅방 메세지 전달하는 메소드
    private void send(ChattingContentDTO chattingContentDTO, ObjectMapper objectMapper) throws IOException {
        log.info("=========send");
        log.info(chattingContentDTO.toString());

        TextMessage textMessage = new TextMessage(objectMapper.
                writeValueAsString(chattingContentDTO.getChattingContent()));
//       채팅방 세션이랑 채팅방 아이디 값이 같으면 메세지 전달
        for (WebSocketSession sess : sessions.values()) {
            if (sess.getAttributes().get("chattingRoomId") == chattingContentDTO.getChattingRoomId()) {
                sess.sendMessage(textMessage);
            }
        }

        log.info(sessions.toString());
    }

}




