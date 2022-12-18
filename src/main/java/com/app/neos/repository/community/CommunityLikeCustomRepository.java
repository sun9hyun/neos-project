package com.app.neos.repository.community;

import com.app.neos.entity.community.CommunityLike;

import java.util.List;

public interface CommunityLikeCustomRepository {
//    내가 좋아요한 게시글
//    public List<CommunityLike> findAllByCommunityLikeId(Long communityLikeId);

//    내가 게시글을 좋아요 했는지 중복 검사
    public boolean duplicate(Long userId, Long communityId);

//    내가 좋아요한 게시글 찾기
    public CommunityLike findByIdAndCommunityId(Long userId, Long communityId);

}
