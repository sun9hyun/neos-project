package com.app.neos.domain.chatting;



import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Component
@Data
@NoArgsConstructor
public class ChattingDTO {

    private Long chattingId;
    private User myId;
    private User receiverId;
    private LocalDateTime createdDate;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @QueryProjection
    public ChattingDTO(Long chattingId, User myId, User receiverId, LocalDateTime createdDate) {
        this.chattingId = chattingId;
        this.myId = myId;
        this.receiverId = receiverId;
        this.createdDate = createdDate;

    }
    @QueryProjection
    public ChattingDTO(Long chattingId, User myId, User receiverId, Set<WebSocketSession> sessions) {
        this.chattingId = chattingId;
        this.myId = myId;
        this.receiverId = receiverId;
        this.sessions = sessions;
    }

    public ChattingDTO create(String name){
        ChattingDTO chatRoom = new ChattingDTO();
        chatRoom.chattingId = chattingId;
        chatRoom.myId = myId;
        chatRoom.receiverId = receiverId;
        chatRoom.createdDate = createdDate;
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, ChattingContentDTO chattingContentDTO,
                              ObjectMapper objectMapper) throws IOException {
        if(chattingContentDTO.getChatType() == ChatType.ENTER){
            sessions.add(session);
            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver() + "님이 입장하셨습니다.");
        }
        else if(chattingContentDTO.getChatType() == ChatType.LEAVE){
            sessions.remove(session);
            chattingContentDTO.setChattingContent(chattingContentDTO.getReceiver() + "님임 퇴장하셨습니다.");
        }
        else{
            chattingContentDTO.setChattingContent(chattingContentDTO + " : " + chattingContentDTO.getChattingContent());
        }
        send(chattingContentDTO,objectMapper);
    }

    private void send(ChattingContentDTO chattingContentDTO, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.
                writeValueAsString(chattingContentDTO.getChattingContent()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
    }
}
