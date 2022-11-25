package com.app.neosproject.entity.community;


import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_REPLY")
@Getter
@ToString(exclude = {"user","community"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityReply extends Period {

    @Id @GeneratedValue
    private Long communityReplyId;

    private String communityReplyContent;

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
    public CommunityReply(String communityReplyContent, User user, Community community) {
        this.communityReplyContent = communityReplyContent;
        this.user = user;
        this.community = community;
    }
}
