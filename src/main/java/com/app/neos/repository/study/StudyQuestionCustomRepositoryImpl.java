package com.app.neos.repository.study;

import com.app.neos.domain.study.QStudyQuestionDTO;
import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.entity.study.QStudyQuestion;
import com.app.neos.entity.study.StudyQuestion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.app.neos.entity.study.QStudyQuestion.*;

@Repository
@RequiredArgsConstructor
public class StudyQuestionCustomRepositoryImpl implements StudyQuestionCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<StudyQuestion> findByStudyId(Long studyId) {
        return jpaQueryFactory.selectFrom(studyQuestion)
                .where(studyQuestion.study.studyId.eq(studyId))
                .orderBy(studyQuestion.studyQuestionId.desc())
                .fetch();
    }


}
