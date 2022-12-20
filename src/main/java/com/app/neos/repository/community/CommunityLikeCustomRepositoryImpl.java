package com.app.neos.repository.community;

import com.app.neos.entity.community.CommunityLike;
import com.app.neos.entity.community.QCommunityLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.community.QCommunityLike.communityLike;

@Repository
@RequiredArgsConstructor
public class CommunityLikeCustomRepositoryImpl implements CommunityLikeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean duplicate(Long userId, Long communityId) {
        List<CommunityLike> communityLikes = jpaQueryFactory.selectFrom(communityLike)
                .where(communityLike.user.userId.eq(userId).and(communityLike.community.communityId.eq(communityId)))
                .fetch();

        //0이 들어가는지
        System.out.println("********************* communityLikes.size() : " + communityLikes.size() + "*********************");


        return communityLikes.size() != 0;
    }

    //내가 좋아요한 게시글 찾기
    @Override
    public CommunityLike findByIdAndCommunityId(Long userId, Long communityId) {
        return jpaQueryFactory.selectFrom(communityLike)
                .where(communityLike.user.userId.eq(userId).and(communityLike.community.communityId.eq(communityId)))
                .fetchOne();
    }

    @Override
    public Boolean checkLike(Long userId, Long communityId) {
        boolean check;

        List<CommunityLike> communityLikes = jpaQueryFactory.selectFrom(communityLike)
                .where(communityLike.user.userId.eq(userId).and(communityLike.community.communityId.eq(communityId)))
                .fetch();

        if(communityLikes.size() == 0){
            check = false;
        }else{
            check = true;
        }

        return check;
    }

//  내가 좋아요한 게시글
//    @Override
//    public List<CommunityLike> findAllByCommunityLikeId(Long userId) {
//        return jpaQueryFactory.selectFrom(QCommunityLike.communityLike)
//                .where(QCommunityLike.communityLike.user.userId.eq(userId))
//                .fetch();
//    }


}
