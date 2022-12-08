package com.app.neos.entity.chatting;


import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Table(name="TBL_CHATTING_CONTENT")
@ToString(exclude = {"my", "receiver", "chatting"}) @Getter
//@Inheritance (strategy = InheritanceType.JOINED)
@NoArgsConstructor
public  class ChattingContent extends Created {
    @Id @GeneratedValue
    private Long chattingContentId;
    @NotNull
    private String chattingContent;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "MY_ID")
    private User my;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATTING_ID")
    private Chatting chatting;

    @Enumerated(EnumType.STRING) @NotNull
    private ChatType chatType;

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
    public ChattingContent(@NotNull String chattingContent, ChatType chatType) {
        this.chattingContent = chattingContent;
        this.chatType = chatType;
    }

    public void update(String chattingContent){
        this.chattingContent = chattingContent;
    }
}
