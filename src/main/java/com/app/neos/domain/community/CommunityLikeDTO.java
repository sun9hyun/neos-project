package com.app.neos.domain.community;

import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityLike;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityLikeDTO {
    private Long communityLikeId;
    private User user;
    private Community community;
    private Long communityId;

    public CommunityLike toEntity(){
        return CommunityLike.builder()
                .build();
    }

    @QueryProjection
    public CommunityLikeDTO(Long communityLikeId, User user, Community community){
        this.communityLikeId = communityLikeId;
        this.user = user;
        this.community = community;
    }

    @QueryProjection
    public CommunityLikeDTO(Long communityLikeId, User user, Long communityId){
        this.communityLikeId = communityLikeId;
        this.user = user;
        this.communityId = communityId;
    }

}
