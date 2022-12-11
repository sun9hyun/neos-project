package com.app.neos.repository.community;

import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityLike;
import com.app.neos.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    Optional<CommunityLike> findByCommunityAndUser(Long communityId, Long userId);
}
