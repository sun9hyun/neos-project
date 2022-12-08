package com.app.neos.repository.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.app.neos.service.admin.AdminService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.neos.entity.study.QStudy.study;
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
    public Page<StudyDTO> findAllStudyPage(Pageable pageable) {

        List<StudyDTO> studyDTOS = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.user.userNickName,
                study.createdDate,
                study.followList.size()
                ))
                .from(study)
                .orderBy(study.studyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.user.userNickName,
                study.createdDate,
                study.followList.size()
        ))
                .from(study)
                .fetch().size();

        return new PageImpl<>(studyDTOS,pageable,total);
    }

    @Override
    public Page<CommunityDTO> findAllCommunityPage(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CounselingDTO> findAllCounselingPage(Pageable pageable) {
        return null;
    }

    @Override
    public Page<StoreDTO> findAllStorePage(Pageable pageable) {
        return null;
    }

//    @Override
//    public Page<UserDTO> findAllUserPage(Pageable pageable) {
//
//
//        List<UserDTO> userDTOS = jpaQueryFactory.select(new QUserDTO(
//                user.userId,
//                user.userNickName,
//                user.userOAuthId,
//                user.userOAuthEmail,
//                user.userCollegeEmail,
//                user.userPhoneNumber,
//                user.userCollegeCertify,
//                user.userCollegeInfo.userCollegeYear,
//                user.userCollegeInfo.userCollegeMajor,
//                user.userNeosPower.userNeosBadge,
//                user.userNeosPower.userNeosPowerLevel,
//                user.userNeosPower.userNeosPowerAbility,
//                user.userNeosPoint,
//                user.userChattingPoint,
//                user.userLike.userO2o,
//                user.userLike.userCity,
//                user.userLike.userDay,
//                user.userLike.userTime,
//                user.userMBTI.userMbtiName,
//                user.userMBTI.userMbtiColor,
//                user.userIntroduce,
//                user.userFile,
//                user.createdDate
//                ))
//                .from(user)
//                .orderBy(user.userId.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        for (UserDTO user: userDTOS) {
//            user.setCounts(adminService.workCount(user.getUserId()));
//        }
//
//
//        long total = jpaQueryFactory.select(new QUserDTO(
//                user.userId,
//                user.userNickName,
//                user.userOAuthId,
//                user.userOAuthEmail,
//                user.userCollegeEmail,
//                user.userPhoneNumber,
//                user.userCollegeCertify,
//                user.userCollegeInfo.userCollegeYear,
//                user.userCollegeInfo.userCollegeMajor,
//                user.userNeosPower.userNeosBadge,
//                user.userNeosPower.userNeosPowerLevel,
//                user.userNeosPower.userNeosPowerAbility,
//                user.userNeosPoint,
//                user.userChattingPoint,
//                user.userLike.userO2o,
//                user.userLike.userCity,
//                user.userLike.userDay,
//                user.userLike.userTime,
//                user.userMBTI.userMbtiName,
//                user.userMBTI.userMbtiColor,
//                user.userIntroduce,
//                user.userFile,
//                user.createdDate
//                ))
//                .from(user)
//                .fetch().size();
//
//        return new PageImpl<>(userDTOS,pageable,total);
//
//    }


}
