package com.app.neos.repository.admin;

import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.user.QUser.user;


@Repository
@RequiredArgsConstructor
public class AdminCustomRepositoryImpl implements AdminCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public User findByUserId(Long userId) {
        User user = jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.userId.eq(userId))
                .fetchOne();

        return user;
    }

    @Override
    public UserDTO findByUserDTOId(Long userId) {
        UserDTO userDTO = jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.userOAuthId,
                user.userOAuthEmail,
                user.userCollegeEmail,
                user.userPhoneNumber,
                user.userCollegeCertify,
                user.userCollegeInfo.userCollegeYear,
                user.userCollegeInfo.userCollegeMajor,
                user.userNeosPower.userNeosBadge,
                user.userNeosPower.userNeosPowerLevel,
                user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,
                user.userChattingPoint,
                user.userLike.userO2o,
                user.userLike.userCity,
                user.userLike.userDay,
                user.userLike.userTime,
                user.userMBTI.userMbtiName,
                user.userMBTI.userMbtiColor,
                user.userIntroduce,
                user.userFile,
                user.createdDate
                ))
                .from(user)
                .where(user.userId.eq(userId))
                .fetchOne();

        return userDTO;
    }

    @Override
    public Page<UserDTO> findAllUserPage(Pageable pageable) {
        List<UserDTO> userDTOS = jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.userOAuthId,
                user.userOAuthEmail,
                user.userCollegeEmail,
                user.userPhoneNumber,
                user.userCollegeCertify,
                user.userCollegeInfo.userCollegeYear,
                user.userCollegeInfo.userCollegeMajor,
                user.userNeosPower.userNeosBadge,
                user.userNeosPower.userNeosPowerLevel,
                user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,
                user.userChattingPoint,
                user.userLike.userO2o,
                user.userLike.userCity,
                user.userLike.userDay,
                user.userLike.userTime,
                user.userMBTI.userMbtiName,
                user.userMBTI.userMbtiColor,
                user.userIntroduce,
                user.userFile,
                user.createdDate
                ))
                .from(user)
                .orderBy(user.userId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.userOAuthId,
                user.userOAuthEmail,
                user.userCollegeEmail,
                user.userPhoneNumber,
                user.userCollegeCertify,
                user.userCollegeInfo.userCollegeYear,
                user.userCollegeInfo.userCollegeMajor,
                user.userNeosPower.userNeosBadge,
                user.userNeosPower.userNeosPowerLevel,
                user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,
                user.userChattingPoint,
                user.userLike.userO2o,
                user.userLike.userCity,
                user.userLike.userDay,
                user.userLike.userTime,
                user.userMBTI.userMbtiName,
                user.userMBTI.userMbtiColor,
                user.userIntroduce,
                user.userFile,
                user.createdDate
                ))
                .from(user)
                .fetch().size();

        return new PageImpl<>(userDTOS,pageable,total);

    }


}
