package com.app.neos.domain.follow;

import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class FollowDTO {
    private Long followId;
    private User myId;
    private User followingId;

    @QueryProjection
    public FollowDTO(Long followId, User myId, User followingId) {
        this.followId = followId;
        this.myId = myId;
        this.followingId = followingId;
    }
}
