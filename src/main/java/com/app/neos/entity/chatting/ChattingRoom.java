package com.app.neos.entity.chatting;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingRoomDTO;
import com.app.neos.entity.period.ChatPeriod;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.app.neos.type.chatting.MessageType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="TBL_CHATTING_ROOM")
@Getter
@ToString
@NoArgsConstructor

public class ChattingRoom extends ChatPeriod {
    @Id @GeneratedValue

    private Long chattingRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MY_ROOM_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User myRoom;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ROOM_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User receiverRoom;

    @Builder
    public ChattingRoom( User myRoom,User receiverRoom,ChattingContent chattingContentIdRoom,String chattingContentRoom, ChatType chatType) {
        this.myRoom = myRoom;
        this.receiverRoom = receiverRoom;
    }

    public void changeMyRoom(User myRoom){
        this.myRoom = myRoom;
    }
    public void changeReceiverRoom(User receiverRoom){
        this.receiverRoom = receiverRoom;
    }


}
