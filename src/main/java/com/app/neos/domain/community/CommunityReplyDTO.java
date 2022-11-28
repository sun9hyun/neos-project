package com.app.neos.domain.community;

import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityReplyDTO {

    private Long communityReplyId;
    private String communityReplyContent;
    private int communityReplyLikeCount;
    private User user;
    private Community community;

    public CommunityReply toEntity(){
        return CommunityReply.builder()
                .communityReplyContent(communityReplyContent)
                .communityReplyLikeCount(communityReplyLikeCount)
                .build();
    }

    @QueryProjection
    public CommunityReplyDTO(Long communityReplyId, String communityReplyContent, int communityReplyLikeCount, User user, Community community) {
        this.communityReplyId = communityReplyId;
        this.communityReplyContent = communityReplyContent;
        this.communityReplyLikeCount = communityReplyLikeCount;
        this.user = user;
        this.community = community;
    }
}
