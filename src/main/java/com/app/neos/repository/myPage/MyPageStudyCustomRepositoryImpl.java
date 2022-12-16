package com.app.neos.repository.myPage;

import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.study.QStudyFollow;
import com.app.neos.entity.study.StudyFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudy.study;

@Repository
@RequiredArgsConstructor
public class MyPageStudyCustomRepositoryImpl implements MyPageStudyCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;



    @Override
    public List<StudyFollow> findStudyFollow(Long userId) {
        return jpaQueryFactory.selectFrom(QStudyFollow.studyFollow)
                .where(QStudyFollow.studyFollow.user.userId.eq(userId))
                .fetch();
    }

    @Override
    public StudyDTO findStudyDTO(Long studyId) {
        StudyDTO studyDTO = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.studyField.studyType,
                study.studyField.studyKeyword,
                study.studyOnlineWhether.studyO2o,
                study.studyOnlineWhether.studyCity,
                study.studySupport,
                study.studyRecruitStatus,
                study.studyStatus,
                study.studyContent,
                study.studyView,
                study.studyEndDate,
                study.user.college.collegeId,
                study.user.college.collegeLogoFile,
                study.user.college.collegeName,
                study.user.userId,
                study.user.userNickName
        ))
                .from(study)
                .where(study.studyId.eq(studyId))
                .fetchOne();

        return studyDTO;
    }

    @Override // 이거 수정해야함
    public List<StudyDTO> findMemberStudyByUserId(Long userId) {
        return jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.studyField.studyType,
                study.studyField.studyKeyword,
                study.studyOnlineWhether.studyO2o,
                study.studyOnlineWhether.studyCity,
                study.studySupport,
                study.studyRecruitStatus,
                study.studyStatus,
                study.studyContent,
                study.studyView,
                study.studyEndDate
        ))
                .from(study)
//                .where(QStudy.study.user.userId.eq(userId),QStudy.study.studyStatus)
                .where(study.user.userId.eq(userId))
                .orderBy(study.studyId.desc())
                .fetch();
    }

    @Override 
    public List<StudyDTO> findStudyByUserId(Long userId) {
        return jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.studyField.studyType,
                study.studyField.studyKeyword,
                study.studyOnlineWhether.studyO2o,
                study.studyOnlineWhether.studyCity,
                study.studySupport,
                study.studyRecruitStatus,
                study.studyStatus,
                study.studyContent,
                study.studyView,
                study.studyEndDate
                ))
                .from(study)
//                .where(QStudy.study.user.userId.eq(userId),QStudy.study.studyStatus)
                .where(study.user.userId.eq(userId))
                .orderBy(study.studyId.desc())
                .fetch();
    }
}
