package com.app.neos.entity.community;

import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_LIKE")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityLike extends Created {
    @Id @GeneratedValue
    private Long communityLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Community community;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeCommunity(Community community){
        this.community =community;
    }

    @Builder
    public CommunityLike(@NotNull User user, @NotNull Community community ){
        this.user = user;
        this.community = community;
    }

}
