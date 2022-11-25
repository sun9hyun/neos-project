package com.app.neosproject.entity.chatting;


import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
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


    public void changeMyId(User myId){
        this.myId = myId;
    }

    public void changeReceiverId(User receiverId){
        this.receiverId = receiverId;
    }
    @Builder
    public Chatting(User myId, User receiverId) {
        this.myId = myId;
        this.receiverId = receiverId;
    }
}
