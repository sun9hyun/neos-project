package com.app.neos.repository.myPage;

import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.QStudy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageStudyCustomRepositoryImpl implements MyPageStudyCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StudyDTO> findStudyByUserId(Long userId) {
        return jpaQueryFactory.select(new QStudyDTO(
                QStudy.study.studyId,
                QStudy.study.studyTitle,
                QStudy.study.studyField.studyType,
                QStudy.study.studyField.studyKeyword,
                QStudy.study.studyOnlineWhether.studyO2o,
                QStudy.study.studyOnlineWhether.studyCity,
                QStudy.study.studySupport,
                QStudy.study.studyRecruitStatus,
                QStudy.study.studyStatus,
                QStudy.study.studyContent,
                QStudy.study.studyView,
                QStudy.study.studyEndDate
                ))
                .from(QStudy.study)
//                .where(QStudy.study.user.userId.eq(userId),QStudy.study.studyStatus)
                .where(QStudy.study.user.userId.eq(userId))
                .orderBy(QStudy.study.studyId.desc())
                .fetch();
    }
}
