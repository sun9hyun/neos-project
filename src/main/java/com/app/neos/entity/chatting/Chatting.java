package com.app.neos.entity.chatting;


import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.app.neos.type.chatting.MessageType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_CHATTING")
@Getter
@ToString(exclude = {"myId","receiverId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting extends Created {
    @Id @GeneratedValue
    private Long chattingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MY_ID_USER_ID")
    private User myId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RECEIVER_ID_USER_ID")
    private User receiverId;

    @Enumerated(EnumType.STRING) @NotNull
    private ChatType chatType;

    @Builder
    public Chatting(User myId, User receiverId,ChatType chatType) {
        this.myId = myId;
        this.receiverId = receiverId;
        this.chatType = chatType;
    }


    public void changeMyId(User myId){
        this.myId = myId;
    }
    public void changeReceiverId(User receiverId){
        this.receiverId = receiverId;
    }

}
