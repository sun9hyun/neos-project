package com.app.neos.service.study;


import com.app.neos.entity.study.QStudyQuestion;
import com.app.neos.entity.study.StudyQuestionReply;
import com.app.neos.repository.study.StudyQuestionReplyCustomRepository;
import com.app.neos.repository.study.StudyQuestionRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.neos.entity.study.QStudyQuestion.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class StudyQuestionService {

    @Autowired
    StudyQuestionRepository studyQuestionRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Autowired
    StudyQuestionReplyCustomRepository studyQuestionReplyCustomRepository;


    @Test
    public void test1(){
        log.info(jpaQueryFactory.selectFrom(studyQuestion).where(studyQuestion.study.studyId.eq(235l)).fetchOne().toDTO().toString());
    }

    @Test
    public void test2(){
        studyQuestionReplyCustomRepository.findAllByStudyQuestionId(246l).stream().map(StudyQuestionReply::toDTO).forEach(i->log.info(i.toString()));
    }





}
