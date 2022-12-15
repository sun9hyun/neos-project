package com.app.neos.repository.follow;

import com.app.neos.domain.user.FollowDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.user.User;

import java.util.List;

public interface FollowCustomRepository {

//    내가 상대를 팔로우 한거 팔로우 Follow

    public List<Follow> findAllByMyId(Long myId);

//    상대가 나를 팔로우 한것 팔로잉  Following

    public List<Follow> findByFollowingId(Long followingId);
}
