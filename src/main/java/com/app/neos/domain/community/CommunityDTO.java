package com.app.neos.domain.community;

import com.app.neos.entity.community.Community;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CommunityDTO {
    private Long communityId;
    private String communityTitle;
    private String communityContent;
    private int communityLikeCount;
    private User user;
    private Long userId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private String userNickName;

    private Boolean checkLike;

    public Community toEntity(){
        return Community.builder()
                .communityTitle(communityTitle)
                .communityContent(communityContent)
                .communityLikeCount(communityLikeCount)
                .build();
    }


    @QueryProjection
    public CommunityDTO(Long communityId, String communityTitle, String communityContent, int communityLikeCount, User user, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.communityId = communityId;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityLikeCount = communityLikeCount;
        this.user = user;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @QueryProjection
    public CommunityDTO(Long communityId, String communityTitle, String communityContent, int communityLikeCount, Long userId) {
        this.communityId = communityId;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityLikeCount = communityLikeCount;
        this.userId = userId;
    }

    @QueryProjection
    public CommunityDTO(Long communityId, String communityTitle, int communityLikeCount, String userNickName, LocalDateTime createdDate) {
        this.communityId = communityId;
        this.communityTitle = communityTitle;
        this.communityLikeCount = communityLikeCount;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public CommunityDTO(Long communityId, String communityTitle, int communityLikeCount, String userNickName, LocalDateTime createdDate, Long userId) {
        this.communityId = communityId;
        this.communityTitle = communityTitle;
        this.communityLikeCount = communityLikeCount;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    @QueryProjection
    public CommunityDTO(Long communityId, String communityTitle, String communityContent, int communityLikeCount, User user, LocalDateTime createdDate, LocalDateTime updatedDate, Boolean checkLike) {
        this.communityId = communityId;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityLikeCount = communityLikeCount;
        this.user = user;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.checkLike = checkLike;
    }


}
