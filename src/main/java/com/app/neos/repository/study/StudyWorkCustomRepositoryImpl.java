package com.app.neos.repository.study;


import com.app.neos.entity.study.QStudyWork;
import com.app.neos.entity.study.StudyWork;
import com.app.neos.type.study.work.StudyWorkStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudyWork.*;

@Repository
@RequiredArgsConstructor
public class StudyWorkCustomRepositoryImpl implements StudyWorkCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StudyWork> findAllProceeding(Long studyId) {
        return jpaQueryFactory.selectFrom(studyWork).where(studyWork.study.studyId.eq(studyId).and(studyWork.studyWorkStatus.eq(StudyWorkStatus.PROCEEDING))).
                orderBy(studyWork.studyWorkId.desc()).fetch();
    }

    @Override
    public List<StudyWork> findAllFinish(Long studyId) {
        return jpaQueryFactory.selectFrom(studyWork).where(studyWork.study.studyId.eq(studyId).and(studyWork.studyWorkStatus.eq(StudyWorkStatus.FINISH))).orderBy(studyWork.studyWorkId.desc()).fetch();
    }
}
