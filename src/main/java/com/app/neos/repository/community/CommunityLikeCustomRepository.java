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


//    세션 아이디랑 커뮤니티 아이디랑 비교해서 있으면 (좋아요 했다는 것) true 없으면 (좋아요 아직 안했다는 것) false
    public Boolean checkLike(Long userId, Long communityId);

}
