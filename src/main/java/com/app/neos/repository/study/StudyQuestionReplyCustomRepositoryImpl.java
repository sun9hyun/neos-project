package com.app.neos.repository.study;


import com.app.neos.domain.study.QStudyQuestionReplyDTO;
import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.study.QStudyQuestionReply;
import com.app.neos.entity.study.StudyQuestionReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudyQuestionReply.*;

@Repository
@RequiredArgsConstructor
public class StudyQuestionReplyCustomRepositoryImpl implements StudyQuestionReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<StudyQuestionReply> findAllByStudyQuestionId(Long studyQuestionId) {
        return jpaQueryFactory.selectFrom(studyQuestionReply).where(studyQuestionReply.studyQuestion.studyQuestionId.eq(studyQuestionId)).orderBy(studyQuestionReply.studyQuestionReplyId.desc()).fetch();
    }



}
