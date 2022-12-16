package com.app.neos.repository.myPage;

import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.follow.QFollow;
import com.app.neos.entity.study.QStudyFollow;
import com.app.neos.entity.study.StudyFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageFollowCustomRepositoryImpl implements MyPageFollowCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Follow> findByUserId(Long userId) {
        return jpaQueryFactory.selectFrom(QFollow.follow)
                .where(QFollow.follow.myId.userId.eq(userId)).fetch();
    }

}
