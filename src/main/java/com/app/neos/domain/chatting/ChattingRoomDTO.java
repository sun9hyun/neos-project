package com.app.neos.domain.chatting;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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


    public ChattingRoom toEntity(){
        return ChattingRoom.builder()
                .chattingContentRoom(chattingContentRoom)
                .chatType(chatType)
                .build();
    }

    @QueryProjection
    public ChattingRoomDTO(Long chattingRoomId,String chattingContentRoom,User myRoom,User receiverRoom,ChattingContent chattingContentIdRoom,ChatType chatType,LocalDateTime updatedDate){
        this.chattingRoomId =chattingRoomId;
        this.chattingContentRoom =chattingContentRoom;
        this.myRoom =myRoom;
        this.receiverRoom =receiverRoom;
        this.chattingContentIdRoom =chattingContentIdRoom;
        this.chatType =chatType;
        this.updatedDate = updatedDate;
    }


}
