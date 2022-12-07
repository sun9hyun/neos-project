package com.app.neos.entity.community;


import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends Period {
    @Id @GeneratedValue
    private Long communityId;
    @NotNull
    private String communityTitle;
    @NotNull
    private String communityContent;
    @NotNull
    private int communityLikeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Community(@NotNull String communityTitle, @NotNull String communityContent, @NotNull int communityLikeCount) {
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityLikeCount = communityLikeCount;
    }

    public void updateCommunityLikeCount(CommunityDTO communityDTO){
        this.communityLikeCount = communityDTO.getCommunityLikeCount();
    }

    public void update(CommunityDTO communityDTO){
        this.communityTitle = communityDTO.getCommunityTitle();
        this.communityContent = communityDTO.getCommunityContent();
    }


}
