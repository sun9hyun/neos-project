package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyQuestionRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class StudyQuestionEntityTest {
    @Autowired
    StudyQuestionRepository studyQuestionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudyRepository studyRepository;


    @Test
    public void saveTest(){
        StudyQuestionDTO studyQuestionDTO = new StudyQuestionDTO();
        studyQuestionDTO.setStudyQuestionContent("질문이 있어요!!");

        StudyQuestion studyQuestion = studyQuestionDTO.toEntity();

        studyQuestionRepository.save(studyQuestion);
    }

    @Test
    public void updateTest(){
        StudyQuestion studyQuestion = studyQuestionRepository.findById(13L).get();
        StudyQuestionDTO studyQuestionDTO = new StudyQuestionDTO();
        studyQuestionDTO.setStudyQuestionContent("질문이 있었는데! 없어요!!");

    }

    @Test
    public void findTest(){
        studyQuestionRepository.findAll().stream().map(StudyQuestion::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        studyQuestionRepository.deleteById(13L);
    }


}
