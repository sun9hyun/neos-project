package com.app.neos.entity.chatting;


import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_CHATTING")
@Getter
@ToString(exclude = {"myId","receiverId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting extends Created {
    @Id @GeneratedValue @NonNull
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

}
