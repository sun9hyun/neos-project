package com.app.neosproject.entity.chatting;


import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_CHATTING_CONTENT")
@ToString(exclude = {"my", "receiver", "chatting"}) @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingContent extends Created {
    @Id @GeneratedValue
    private Long chattingContentId;

    private String chattingContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MY_ID")
    private User  my;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATTING_ID")
    private Chatting chatting;


    public void changeMy(User my){
        this.my = my;
    }
    public void changeReceiver(User receiver){
        this.receiver = receiver;
    }
    public void changeChatting(Chatting chatting ){
        this.chatting = chatting;
    }

    @Builder
    public ChattingContent(String chattingContent, User my, User receiver, Chatting chatting) {
        this.chattingContent = chattingContent;
        this.my = my;
        this.receiver = receiver;
        this.chatting = chatting;
    }
}
