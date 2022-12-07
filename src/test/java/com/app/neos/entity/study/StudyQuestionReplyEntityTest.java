package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyQuestionReplyRepository;
import com.app.neos.repository.study.StudyQuestionRepository;
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
public class StudyQuestionReplyEntityTest {
    @Autowired
    StudyQuestionReplyRepository studyQuestionReplyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudyQuestionRepository studyQuestionRepository;


    @Test
    public void saveTest(){
        StudyQuestionReplyDTO studyQuestionReplyDTO = new StudyQuestionReplyDTO();
        studyQuestionReplyDTO.setStudyQuestionReplyContent("아이 참 그거 이거에요!");

        StudyQuestionReply studyQuestionReply = studyQuestionReplyDTO.toEntity();

        studyQuestionReplyRepository.save(studyQuestionReply);
    }


    @Test
    public void updateTest(){
        StudyQuestionReply studyQuestionReply = studyQuestionReplyRepository.findById(14L).get();
        StudyQuestionReplyDTO studyQuestionReplyDTO = new StudyQuestionReplyDTO();
        studyQuestionReplyDTO.setStudyQuestionReplyContent("아이 참 그거 아니에요!!");

        studyQuestionReply.update(studyQuestionReplyDTO);
    }

    @Test
    public void findTest(){
        studyQuestionReplyRepository.findAll().stream().map(StudyQuestionReply::toString).forEach(log::info);
    }


    @Test
    public void deleteTest(){
        studyQuestionReplyRepository.deleteById(14L);
    }
}
