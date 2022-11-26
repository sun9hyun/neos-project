package com.app.neos.domain.community;

import com.app.neos.entity.community.CommunityReReply;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityReReplyDTO {

    private Long reReplyId;
    private String reReplyContent;
    private User user;
    private CommunityReply communityReply;

    public CommunityReReply toEntity(){
        return CommunityReReply.builder()
                .reReplyContent(reReplyContent)
                .build();
    }

    @QueryProjection
    public CommunityReReplyDTO(Long reReplyId, String reReplyContent, User user, CommunityReply communityReply) {
        this.reReplyId = reReplyId;
        this.reReplyContent = reReplyContent;
        this.user = user;
        this.communityReply = communityReply;
    }
}
