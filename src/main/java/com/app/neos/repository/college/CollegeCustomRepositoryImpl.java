package com.app.neos.repository.college;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.college.QCollege;
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
public class CollegeCustomRepositoryImpl implements CollegeCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<CollegeDTO> findAll() {
        return jpaQueryFactory.select(new QCollegeDTO(
                QCollege.college.collegeId,
                QCollege.college.collegeCity,
                QCollege.college.collegeName,
                QCollege.college.collegeLogoFile,
                QCollege.college.collegeEmailDomain,
                QCollege.college.createdDate,
                QCollege.college.updatedDate
                ))
                .from(QCollege.college)
                .orderBy(QCollege.college.collegeId.desc())
                .fetch();
    }

    @Override
    public Page<CollegeDTO> findAllPage(Pageable pageable) {

        List<CollegeDTO> collegeDTOS= jpaQueryFactory.select(new QCollegeDTO(
                QCollege.college.collegeId,
                QCollege.college.collegeCity,
                QCollege.college.collegeName,
                QCollege.college.collegeLogoFile,
                QCollege.college.collegeEmailDomain,
                QCollege.college.createdDate,
                QCollege.college.updatedDate
                ))
                .from(QCollege.college)
                .orderBy(QCollege.college.collegeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = jpaQueryFactory.select(new QCollegeDTO(
                QCollege.college.collegeId,
                QCollege.college.collegeCity,
                QCollege.college.collegeName,
                QCollege.college.collegeLogoFile,
                QCollege.college.collegeEmailDomain,
                QCollege.college.createdDate,
                QCollege.college.updatedDate
                ))
                .from(QCollege.college).fetch().size();

        return new PageImpl<>(collegeDTOS,pageable,total);
    }

    @Override
    public CollegeDTO findByCollegeId(Long collegeId) {
        return jpaQueryFactory.select(new QCollegeDTO(
                QCollege.college.collegeId,
                QCollege.college.collegeCity,
                QCollege.college.collegeName,
                QCollege.college.collegeLogoFile,
                QCollege.college.collegeEmailDomain,
                QCollege.college.createdDate,
                QCollege.college.updatedDate
        )).from(QCollege.college)
                .where(QCollege.college.collegeId.eq(collegeId))
                .fetchOne();
    }

    @Override
    public int countByUser(Long collegeId) {

        return jpaQueryFactory.select(new QUserDTO(user.userId,user.userNickName,user.userOAuthId,user.userOAuthEmail,user.userCollegeEmail,user.userPhoneNumber,user.userCollegeCertify
                ,user.userCollegeInfo.userCollegeYear,user.userCollegeInfo.userCollegeMajor,user.userNeosPower.userNeosBadge,user.userNeosPower.userNeosPowerLevel,user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,user.userChattingPoint,user.userLike.userO2o,user.userLike.userCity,user.userLike.userDay,user.userLike.userTime,user.userMBTI.userMbtiName,user.userMBTI.userMbtiColor,
                user.userIntroduce,user.userFile,user.college.collegeId, user.college.collegeCity,
                user.college.collegeName, user.college.collegeLogoFile, user.college.collegeEmailDomain))
                .from(user)
                .where(user.college.collegeId.eq(collegeId))
                .fetch().size();
    }



}
