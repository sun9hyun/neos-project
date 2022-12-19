package com.app.neos.entity.chatting;


import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.entity.period.ChatPeriod;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.app.neos.type.chatting.MessageType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;

@JsonAutoDetect
@Entity
@Table(name="TBL_CHATTING_CONTENT")
@ToString(exclude = {"writer", "chattingRoom"}) @Getter
//@Inheritance (strategy = InheritanceType.JOINED)
@NoArgsConstructor

public class ChattingContent extends ChatPeriod {
    @Id @GeneratedValue
    private Long chattingContentId;
    @NotNull
    private String chattingContent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATTING_ROOM_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ChattingRoom chattingRoom;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WRITER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User writer;

    @Enumerated(EnumType.STRING) @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MessageType messageType;

    public void changeWriter(User writer){
        this.writer = writer;
    }
    public void changeChattingRoom(ChattingRoom chattingRoom){
        this.chattingRoom = chattingRoom;
    }
    public void changeMessageType(MessageType messageType){
        this.messageType = messageType;
    }

    @Builder
    public ChattingContent(@NotNull Long chattingContentId,String chattingContent, MessageType messageType ,User writer,ChattingRoom chattingRoom) {
        this.chattingContentId=chattingContentId;
        this.chattingContent = chattingContent;
        this.messageType = messageType;
        this.writer = writer;
        this.chattingRoom=chattingRoom;
    }

//    public void update(ChattingContentDTO chattingContentDTO){
//        this.chattingContent = chattingContentDTO.getChattingContent();
//        this.chatting = chattingContentDTO.getChatting();
//        this.writer = chattingContentDTO.();
//        this.chattingContent = chattingContentDTO.getChattingContent();
//    }


}

