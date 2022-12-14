package com.app.neos.repository.myPage;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.app.neos.entity.college.QCollege.college;

@Repository
@RequiredArgsConstructor
public class MyPagCollegeCustomRepositoryImpl implements MyPageCollegeCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void update(User user) {

        System.out.println("*********************" + user + "*********************");

        jpaQueryFactory.update(QUser.user)
                .set(QUser.user.userFile, user.getUserFile())
                .set(QUser.user.userNickName, user.getUserNickName())
                .set(QUser.user.userCollegeInfo, user.getUserCollegeInfo())
                .set(QUser.user.userCollegeCertify, user.getUserCollegeCertify())
                .set(QUser.user.userChattingPoint, user.getUserChattingPoint())
                .set(QUser.user.userLike, user.getUserLike())
                .set(QUser.user.userMBTI, user.getUserMBTI())
                .set(QUser.user.userIntroduce, user.getUserIntroduce())
                .where(QUser.user.userId.eq(user.getUserId()))
                .execute();
    }
}
