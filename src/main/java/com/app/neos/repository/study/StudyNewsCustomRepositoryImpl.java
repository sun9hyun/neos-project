package com.app.neos.repository.study;

import com.app.neos.entity.study.QStudyNews;
import com.app.neos.entity.study.StudyNews;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.app.neos.entity.study.QStudyNews.*;

@Repository
@RequiredArgsConstructor
public class StudyNewsCustomRepositoryImpl implements StudyNewsCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public StudyNews findByStudyIdAndMileStoneId(Long studyId, Long mileStoneId) {
        return jpaQueryFactory.selectFrom(studyNews).where(studyNews.study.studyId.eq(studyId).and(studyNews.studyMilestone.studyMileStoneId.eq(mileStoneId))).fetchOne();
    }

    @Override
    public StudyNews findByStudyIdAndWorkId(Long studyId, Long workId) {
       return jpaQueryFactory.selectFrom(studyNews).where(studyNews.study.studyId.eq(studyId).and(studyNews.studyWork.studyWorkId.eq(workId))).fetchOne();
    }

    @Override
    public StudyNews findByStudyIdAndMemberId(Long studyId, Long memberId) {
        return jpaQueryFactory.selectFrom(studyNews).where(studyNews.study.studyId.eq(studyId).and(studyNews.studyMember.studyMemberId.eq(memberId))).fetchOne();
    }
}
