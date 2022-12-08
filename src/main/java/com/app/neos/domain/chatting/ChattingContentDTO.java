package com.app.neos.domain.chatting;

import com.app.neos.entity.chatting.Chatting;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.user.User;
import com.app.neos.type.chatting.ChatType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ChattingContentDTO {

    private Long chattingContentId;
    private String chattingContent;
    private User my;
    private User receiver;
    private Chatting chatting;
    private ChatType chatType;
    private LocalDateTime createdDate;

    public ChattingContent toEntity(){
        return ChattingContent.builder()
                .chattingContent(chattingContent)
                .chatType(chatType)
                .build();
    }

    @QueryProjection
    public ChattingContentDTO(Long chattingContentId, String chattingContent, User my, User receiver, Chatting chatting, LocalDateTime createdDate) {
        this.chattingContentId = chattingContentId;
        this.chattingContent = chattingContent;
        this.my = my;
        this.receiver = receiver;
        this.chatting = chatting;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public ChattingContentDTO(String chattingContent ,LocalDateTime createdDate) {
        this.chattingContent = chattingContent;
        this.createdDate = createdDate;
    }


}
