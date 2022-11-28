package com.app.neos.entity.chatting;


import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_CHATTING_CONTENT")
@ToString(exclude = {"my", "receiver", "chatting"}) @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingContent extends Created {
    @Id @GeneratedValue
    private Long chattingContentId;
    @NotNull
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
    public ChattingContent(@NotNull String chattingContent) {
        this.chattingContent = chattingContent;
    }
}
