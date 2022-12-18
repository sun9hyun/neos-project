package com.app.neos.repository.myPage;

import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyPageUserCustomRepositoryImpl implements MyPageUserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void update(User user) {

        System.out.println("*********************" + user + "*********************");

        jpaQueryFactory.update(QUser.user)
                .set(QUser.user.userNickName, user.getUserNickName())
                .set(QUser.user.userCollegeInfo, user.getUserCollegeInfo())
                .set(QUser.user.userCollegeCertify, user.getUserCollegeCertify())
                .set(QUser.user.userChattingPoint, user.getUserChattingPoint())
                .set(QUser.user.userMBTI, user.getUserMBTI())
                .set(QUser.user.userIntroduce, user.getUserIntroduce())
                .set(QUser.user.college, user.getCollege())
                .where(QUser.user.userId.eq(user.getUserId()))
                .execute();
    }
}
