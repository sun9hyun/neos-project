package com.app.neos.entity.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="TBL_CHATTING_ROOM")
@Getter
@ToString
@NoArgsConstructor
public class ChattingRoom extends Period {
    @Id @GeneratedValue
    private Long chattingRoomId;
    @NotNull
    private String chattingContentRoom;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "MY_ID_ROOM")
    private User myRoom;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID_ROOM")
    private User receiverRoom;

//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATTING_CONTENT_ID")
    private ChattingContent chattingContentIdRoom;



    @Enumerated(EnumType.STRING) @NotNull
    private ChatType chatType;

    public void changeMyRoom(User myRoom){
        this.myRoom = myRoom;
    }
    public void changeReceiverRoom(User receiverRoom){
        this.receiverRoom = receiverRoom;
    }
    public void changeChattingContentId(ChattingContent chattingContentIdRoom){
        this.chattingContentIdRoom = chattingContentIdRoom;
    }


    @Builder
    public ChattingRoom( User myRoom,User receiverRoom,ChattingContent chattingContentIdRoom,String chattingContentRoom, ChatType chatType) {
        this.myRoom = myRoom;
        this.receiverRoom = receiverRoom;
        this.chattingContentIdRoom = chattingContentIdRoom;
        this.chattingContentRoom = chattingContentRoom;
        this.chatType = chatType;
    }

    public void update(ChattingRoomDTO chattingRoomDTO){
        this.chattingContentRoom = chattingRoomDTO.getChattingContentRoom();
    }
}
