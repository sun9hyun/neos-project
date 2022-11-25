package com.app.neosproject.entity.follow;

import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_FOLLOW")
@Getter @ToString(exclude = {"myId","followingId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow  extends Created {
    @Id @GeneratedValue
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MY_ID_USER_ID")
    private User myId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FOLLOWING_ID_USER_ID")
    private User followingId;

   public void changeMyId(User myId){
        this.myId = myId;
    }

   public void changeFollowingId(User followingId){
        this.followingId = followingId;
    }

    @Builder
    public Follow(User myId, User followingId) {
        this.myId = myId;
        this.followingId = followingId;
    }
}
