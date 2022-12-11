package com.app.neos.repository.study;


import com.app.neos.entity.study.QStudyFeed;
import com.app.neos.entity.study.StudyFeed;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudyFeed.*;

@Repository
@RequiredArgsConstructor
public class StudyFeedCustomRepositoryImpl implements StudyFeedCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<StudyFeed> findAllRecent(Long studyId) {
        return jpaQueryFactory.selectFrom(studyFeed).where(studyFeed.study.studyId.eq(studyId)).orderBy(studyFeed.updatedDate.desc()).fetch();
    }
}
