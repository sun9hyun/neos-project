package com.app.neos.entity.community;


import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends Period {
    @Id @GeneratedValue @NonNull
    private Long communityId;
    @NonNull
    private String communityTitle;
    @NonNull
    private String communityContent;
    @NonNull
    private int communityLikeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void changeUser(User user){
       this.user = user;
    }

    @Builder
    public Community(@NonNull String communityTitle, @NonNull String communityContent) {
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
    }

    public void updateCommunityLikeCount(CommunityDTO communityDTO){
        this.communityLikeCount = communityDTO.getCommunityLikeCount();
    }

    public void update(CommunityDTO communityDTO){
        this.communityTitle = communityDTO.getCommunityTitle();
        this.communityContent = communityDTO.getCommunityContent();
    }


}
