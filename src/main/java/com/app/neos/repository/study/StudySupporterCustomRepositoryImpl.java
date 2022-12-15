package com.app.neos.repository.study;


import com.app.neos.entity.study.QStudySupporter;
import com.app.neos.entity.study.StudySupporter;
import com.app.neos.type.study.supporter.StudySupporterStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudySupporter.*;

@Repository
@RequiredArgsConstructor
public class StudySupporterCustomRepositoryImpl implements StudySupporterCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<StudySupporter> findAllWait(Long studyId) {
        return jpaQueryFactory.selectFrom(studySupporter).where(studySupporter.study.studyId.eq(studyId).and(studySupporter.studySupporterStatus.eq(StudySupporterStatus.WAIT))).fetch();
    }
}
