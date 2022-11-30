package com.app.neos.repository.user;

import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.QCollege;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.neos.entity.user.QUser.*;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<User> findAllPage(Pageable pageable) {

        List<User> users= jpaQueryFactory.selectFrom(user)
               .orderBy(user.userId.desc())
               .offset(pageable.getOffset())
               .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(user).fetch().size();

        return new PageImpl<>(users,pageable,total);
    }

    @Override
    public List<User> findAllSearch(Search search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(search.getUserNickName() != null){
            booleanBuilder.and(user.userNickName.eq(search.getUserNickName()));
        }
        if(search.getUserOAuthId() != null){
            booleanBuilder.and(user.userOAuthId.eq(search.getUserOAuthId()));
        }


        return jpaQueryFactory.selectFrom(user)
                .where(
                        booleanBuilder
                ).fetch();
    }

    @Override
    public List<UserDTO> findAllByOauthId(String oauthId) {
        return jpaQueryFactory.select(new QUserDTO(user.userId,user.userNickName,user.userOAuthId,user.userOAuthEmail,user.userCollegeEmail,user.userPhoneNumber,user.userCollegeCertify
                ,user.userCollegeInfo.userCollegeYear,user.userCollegeInfo.userCollegeMajor,user.userNeosPower.userNeosBadge,user.userNeosPower.userNeosPowerLevel,user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,user.userChattingPoint,user.userLike.userO2o,user.userLike.userCity,user.userLike.userDay,user.userLike.userTime,user.userMBTI.userMbtiName,user.userMBTI.userMbtiColor,
                user.userIntroduce,user.userFile,user.college.collegeId, user.college.collegeCity,
                user.college.collegeName, user.college.collegeLogoFile, user.college.collegeEmailDomain))
                .from(user)
                .fetch();
    }

    @Override
    public UserDTO findByOauthId(String oauthId) {
        return jpaQueryFactory.select(new QUserDTO(user.userId,user.userNickName,user.userOAuthId,user.userOAuthEmail,user.userCollegeEmail,user.userPhoneNumber,user.userCollegeCertify
                ,user.userCollegeInfo.userCollegeYear,user.userCollegeInfo.userCollegeMajor,user.userNeosPower.userNeosBadge,user.userNeosPower.userNeosPowerLevel,user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,user.userChattingPoint,user.userLike.userO2o,user.userLike.userCity,user.userLike.userDay,user.userLike.userTime,user.userMBTI.userMbtiName,user.userMBTI.userMbtiColor,
                user.userIntroduce,user.userFile))
                .from(user)
                .where(user.userOAuthId.eq(oauthId))
                .fetchOne();
    }

    @Override
    public UserDTO findNoCollegeById(Long userId) {
        return jpaQueryFactory.select(new QUserDTO(user.userId,user.userNickName,user.userOAuthId,user.userOAuthEmail,user.userCollegeEmail,user.userPhoneNumber,user.userCollegeCertify
                ,user.userCollegeInfo.userCollegeYear,user.userCollegeInfo.userCollegeMajor,user.userNeosPower.userNeosBadge,user.userNeosPower.userNeosPowerLevel,user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,user.userChattingPoint,user.userLike.userO2o,user.userLike.userCity,user.userLike.userDay,user.userLike.userTime,user.userMBTI.userMbtiName,user.userMBTI.userMbtiColor,
                user.userIntroduce,user.userFile)).from(user).
                where(user.userId.eq(userId))
                .fetchOne();
    }

    @Override
    public UserDTO findById(Long userId) {
        return jpaQueryFactory.select(new QUserDTO(user.userId,user.userNickName,user.userOAuthId,user.userOAuthEmail,user.userCollegeEmail,user.userPhoneNumber,user.userCollegeCertify
                ,user.userCollegeInfo.userCollegeYear,user.userCollegeInfo.userCollegeMajor,user.userNeosPower.userNeosBadge,user.userNeosPower.userNeosPowerLevel,user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,user.userChattingPoint,user.userLike.userO2o,user.userLike.userCity,user.userLike.userDay,user.userLike.userTime,user.userMBTI.userMbtiName,user.userMBTI.userMbtiColor,
                user.userIntroduce,user.userFile,user.college.collegeId, user.college.collegeCity,
                user.college.collegeName, user.college.collegeLogoFile, user.college.collegeEmailDomain))
                .from(user)
                .where(user.userId.eq(userId))
                .fetchOne();
    }


    private BooleanExpression nameEq(String userNickName){
        return StringUtils.hasText(userNickName) ? user.userNickName.eq(userNickName) : null;
    }

    private BooleanExpression oauthEq(String userOAuthId){
        return StringUtils.hasText(userOAuthId) ? user.userOAuthId.eq(userOAuthId) : null;
    }
    private BooleanExpression nameEqOrOauthEq(String userNickName, String userOauthId){
        return nameEq(userNickName).or(oauthEq(userOauthId));
    }

}
