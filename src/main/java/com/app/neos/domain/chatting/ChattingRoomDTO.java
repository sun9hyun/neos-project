package com.app.neos.domain.chatting;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
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
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Data
@NoArgsConstructor
public class ChattingRoomDTO {

    private Long chattingRoomId;
    private String chattingContentRoom;
    private User myRoom;
    private User receiverRoom;
    private ChattingContent chattingContentIdRoom;
    private ChatType chatType;
    private LocalDateTime updatedDate;
    private Long receiverId;
    private static Map<User, WebSocketSession> sessions = new HashMap<>();


    public ChattingRoom toEntity() {
        return ChattingRoom.builder()
                .chattingContentRoom(chattingContentRoom)
                .myRoom(myRoom)
                .receiverRoom(receiverRoom)
                .chattingContentIdRoom(chattingContentIdRoom)
                .chatType(chatType)
                .build();
    }

    @QueryProjection
    public ChattingRoomDTO(Long chattingRoomId, String chattingContentRoom, User myRoom, User receiverRoom, ChattingContent chattingContentIdRoom, LocalDateTime updatedDate) {
        this.chattingRoomId = chattingRoomId;
        this.chattingContentRoom = chattingContentRoom;
        this.myRoom = myRoom;
        this.receiverRoom = receiverRoom;
        this.chattingContentIdRoom = chattingContentIdRoom;
        this.updatedDate = updatedDate;
    }

    /*--웹소켓 핸들러 메세지 설정--- */

    public void handleMessage(WebSocketSession session, ChattingContentDTO chattingContentDTO,
                              ObjectMapper objectMapper) throws IOException {

        ChattingDTO chattingDTO = new ChattingDTO();
//        ChattingRoomDTO chattingRoomDTO = new ChattingRoomDTO();
        log.info("********handleMessage");
        log.info(session.toString());
        log.info(String.valueOf(session.getAttributes().get("receiver")));
        log.info(chattingContentDTO.toString());
        log.info(chattingDTO.toString());
//        if (session.getAttributes().get("receiver") == chattingContentDTO.getReceiver()) {
//            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver().getUserNickName() + "님이 입장하셨습니다.");
//
            sessions.put(chattingContentDTO.getReceiver(), session);
            log.info(sessions.toString());
//        } else {
//            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver().getUserNickName() + " : " + chattingContentDTO.getChattingContent());
//        }

//        sessions.put(chattingRoomDTO.getReceiverRoom(), session);
//        if (session.getAttributes().get("receiver") == chattingContentDTO.getReceiver().getUserId()) {
            send(chattingContentDTO, objectMapper);
//        }
    }


// 확성기 없애기
        private void send (ChattingContentDTO chattingContentDTO, ObjectMapper objectMapper) throws IOException {
            log.info("=========send");
            log.info(chattingContentDTO.toString());
            TextMessage textMessage = new TextMessage(objectMapper.
                    writeValueAsString(chattingContentDTO.getChattingContent()));
            for (WebSocketSession sess : sessions.values())
//            {
//                if (sess.getAttributes().get("receiver") == chattingContentDTO.getReceiver())
                sess.sendMessage(textMessage);
//        }
            log.info(sessions.toString());
        }
    }



