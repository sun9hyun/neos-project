package com.app.neos.repository.study;

import com.app.neos.entity.study.QStudyMilestone;
import com.app.neos.entity.study.StudyMilestone;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudyMilestone.*;

@Repository
@RequiredArgsConstructor
public class StudyMileStoneCustomRepositoryImpl implements StudyMileStoneCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StudyMilestone> findAllMileStone(Long studyId) {
        return jpaQueryFactory.selectFrom(studyMilestone).where(studyMilestone.study.studyId.eq(studyId).and(studyMilestone.studyMilestoneStatus.eq(StudyMilestoneStatus.PROCEEDING))).orderBy(studyMilestone.studyMileStoneId.desc()).fetch();
    }

    @Override
    public List<StudyMilestone> findAllMileStoneFinish(Long studyId) {
        return jpaQueryFactory.selectFrom(studyMilestone).where(studyMilestone.study.studyId.eq(studyId).and(studyMilestone.studyMilestoneStatus.eq(StudyMilestoneStatus.FINISH))).orderBy(studyMilestone.studyMileStoneId.desc()).fetch();
    }
}
