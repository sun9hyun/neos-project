package com.app.neos.repository.follow;

import com.app.neos.domain.user.FollowDTO;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.follow.QFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.follow.QFollow.follow;

@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


//    내가 구독한 사람
    @Override
    public List<Follow> findAllByMyId(Long myId) {
        return jpaQueryFactory.selectFrom(follow)
                .where(follow.myId.userId.eq(myId)).fetch();
    }

//  나를 구독한 사람
    @Override
    public List<Follow> findByFollowingId(Long followingId) {
        return jpaQueryFactory.selectFrom(follow)
                .where(follow.followingId.userId.eq(followingId))
                .fetch();
    }
}
