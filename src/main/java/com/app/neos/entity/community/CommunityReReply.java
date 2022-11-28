package com.app.neos.entity.community;

import com.app.neos.domain.community.CommunityReReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_REREPLY")
@Getter
@ToString(exclude = {"user","communityReply"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityReReply extends Period {

    @Id @GeneratedValue
    private Long reReplyId;
    @NotNull
    private String reReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_REPLY_ID")
    private CommunityReply communityReply;

    public void changeUser(User user){
        this.user =user;
    }

    public void changeCommunityReply(CommunityReply communityReply){
        this.communityReply =communityReply;

    }
    @Builder
    public CommunityReReply(@NotNull String reReplyContent) {
        this.reReplyContent = reReplyContent;
    }

    public void update(CommunityReReplyDTO communityReReplyDTO){
        this.reReplyContent = communityReReplyDTO.getReReplyContent();
    }
}
