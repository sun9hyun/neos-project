package com.app.neos.domain.community;

import com.app.neos.entity.community.Community;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityDTO {
    private Long communityId;
    private String communityTitle;
    private String communityContent;
    private int communityLikeCount;
    private User user;

    public Community toEntity(){
        return Community.builder()
                .communityTitle(communityTitle)
                .communityContent(communityContent)
                .communityLikeCount(communityLikeCount)
                .build();
    }


    @QueryProjection
    public CommunityDTO(Long communityId, String communityTitle, String communityContent, int communityLikeCount, User user) {
        this.communityId = communityId;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityLikeCount = communityLikeCount;
        this.user = user;
    }
}
