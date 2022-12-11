package com.app.neos.repository.study;


import com.app.neos.entity.study.QStudyFeedReply;
import com.app.neos.entity.study.StudyFeedReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudyFeedReply.*;

@Repository
@RequiredArgsConstructor
public class StudyFeedReplyCustomRepositoryImpl implements StudyFeedReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<StudyFeedReply> findAllFeed(Long feedId) {
        return jpaQueryFactory.selectFrom(studyFeedReply).where(studyFeedReply.studyFeed.studyFeedId.eq(feedId)).orderBy(studyFeedReply.updatedDate.desc()).fetch();
    }
}
