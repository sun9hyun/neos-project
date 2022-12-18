package com.app.neos.domain.chatting;

import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.app.neos.type.chatting.MessageType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@Slf4j
@NoArgsConstructor
public class ChattingContentDTO {

    private Long chattingContentId;
    private Long chattingRoomId;
    @JsonIgnore
    private ChattingRoom chattingRoom;
    private String chattingContent;
    private User writer;
    private MessageType messageType;
    private LocalDateTime chatDate;
    private Long writerId;
    private Long userId;


    public ChattingContent toEntity() {
        return ChattingContent.builder()
                .chattingContentId(chattingContentId)
                .chattingContent(chattingContent)
                .messageType(messageType)
                .writer(writer)
                .chattingRoom(chattingRoom)
                .build();
    }

    @QueryProjection
    public ChattingContentDTO(Long chattingContentId, String chattingContent, User writer, ChattingRoom chattingRoom, LocalDateTime chatDate,MessageType messageType) {
        this.chattingContentId = chattingContentId;
        this.chattingContent = chattingContent;
        this.writer = writer;
        this.chattingRoom = chattingRoom;
        this.chatDate = chatDate;
        this.messageType = messageType;
    }
}



