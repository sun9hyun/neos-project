package com.app.neosproject.entity.community;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
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
    public CommunityReReply(String reReplyContent, User user, CommunityReply communityReply) {
        this.reReplyContent = reReplyContent;
        this.user = user;
        this.communityReply = communityReply;
    }
}
