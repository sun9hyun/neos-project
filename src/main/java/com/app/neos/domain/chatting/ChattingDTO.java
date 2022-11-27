package com.app.neos.domain.chatting;


import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ChattingDTO {

    private Long chattingId;
    private User myId;
    private User receiverId;


    @QueryProjection
    public ChattingDTO(Long chattingId, User myId, User receiverId) {
        this.chattingId = chattingId;
        this.myId = myId;
        this.receiverId = receiverId;
    }
}
