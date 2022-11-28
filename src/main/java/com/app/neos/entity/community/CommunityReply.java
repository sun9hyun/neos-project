package com.app.neos.entity.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_REPLY")
@Getter
@ToString(exclude = {"user","community"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityReply extends Period {

    @Id @GeneratedValue @NonNull
    private Long communityReplyId;
    @NonNull
    private String communityReplyContent;
    @NonNull
    private int communityReplyLikeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_ID")
    private Community community;

    public void changeUser(User user){
        this.user = user;

    }
    public void changeCommunity(Community community){
        this.community =community;

    }
    @Builder
    public CommunityReply(@NonNull String communityReplyContent, @NonNull int communityReplyLikeCount) {
        this.communityReplyContent = communityReplyContent;
        this.communityReplyLikeCount = communityReplyLikeCount;
    }

    public void updateCommunityLikeCount(CommunityReplyDTO communityReplyDTO){
        this.communityReplyLikeCount = communityReplyDTO.getCommunityReplyLikeCount();
    }

    public void update(CommunityReplyDTO communityReply){
        this.communityReplyContent = communityReply.getCommunityReplyContent();
        this.communityReplyLikeCount = communityReply.getCommunityReplyLikeCount();
    }
}
