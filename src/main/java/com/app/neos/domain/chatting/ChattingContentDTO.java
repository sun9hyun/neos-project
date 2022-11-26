package com.app.neos.domain.chatting;

import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ChattingContentDTO {

    private Long chattingContentId;
    private String chattingContent;
    private User my;
    private User receiver;
    private Chatting chatting;

    public ChattingContent toEntity(){
        return ChattingContent.builder()
                .chattingContent(chattingContent)
                .build();
    }

    @QueryProjection
    public ChattingContentDTO(Long chattingContentId, String chattingContent, User my, User receiver, Chatting chatting) {
        this.chattingContentId = chattingContentId;
        this.chattingContent = chattingContent;
        this.my = my;
        this.receiver = receiver;
        this.chatting = chatting;
    }
}
