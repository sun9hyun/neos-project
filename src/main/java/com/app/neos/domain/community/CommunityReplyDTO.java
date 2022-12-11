package com.app.neos.domain.community;

import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CommunityReplyDTO {
    private Long communityReplyId;
    private String communityReplyContent;
    private int communityReplyLikeCount;
    private User user;
    private Community community;
    private Long userId;
    private Long communityId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CommunityReply toEntity(){
        return CommunityReply.builder()
                .communityReplyContent(communityReplyContent)
                .communityReplyLikeCount(communityReplyLikeCount)
                .build();
    }

    @QueryProjection
    public CommunityReplyDTO(Long communityReplyId, String communityReplyContent, int communityReplyLikeCount, User user, Community community, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.communityReplyId = communityReplyId;
        this.communityReplyContent = communityReplyContent;
        this.communityReplyLikeCount = communityReplyLikeCount;
        this.user = user;
        this.community = community;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @QueryProjection
    public CommunityReplyDTO(Long communityReplyId, String communityReplyContent, int communityReplyLikeCount, Long userId, Long communityId) {
        this.communityReplyId = communityReplyId;
        this.communityReplyContent = communityReplyContent;
        this.communityReplyLikeCount = communityReplyLikeCount;
        this.userId = userId;
        this.communityId = communityId;
    }

}
