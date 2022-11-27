package com.app.neos.entity.study;


import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyFeedReplyRepository;
import com.app.neos.repository.study.StudyFeedRepository;
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
public class StudyFeedReplyEntityTest {
    @Autowired
    StudyFeedReplyRepository studyFeedReplyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudyFeedRepository studyFeedRepository;


    @Test
    public void saveTest(){
        StudyFeedReplyDTO studyFeedReplyDTO = new StudyFeedReplyDTO();
        studyFeedReplyDTO.setStudyFeedReplyContent("오늘 끝내기 가능!");
        studyFeedReplyDTO.setStudyFeed(studyFeedRepository.findById(7L).get());
        studyFeedReplyDTO.setStudyFeedReplyWriter(userRepository.findById(6L).get());

        StudyFeedReply studyFeedReply = studyFeedReplyDTO.toEntity();
        studyFeedReply.changeStudyFeed(studyFeedReplyDTO.getStudyFeed());
        studyFeedReply.changeStudyFeedReplyWriter(studyFeedReplyDTO.getStudyFeedReplyWriter());

        studyFeedReplyRepository.save(studyFeedReply);
    }

    @Test
    public void updateTest(){
        StudyFeedReply studyFeedReply = studyFeedReplyRepository.findById(8L).get();

        StudyFeedReplyDTO studyFeedReplyDTO = new StudyFeedReplyDTO();
        studyFeedReplyDTO.setStudyFeedReplyContent("오늘 까지 불가능!");

        studyFeedReply.update(studyFeedReplyDTO);

    }

    @Test
    public void findTest(){
        studyFeedReplyRepository.findAll().stream().map(StudyFeedReply::toString).forEach(log::info);
    }


    @Test
    public void deleteStudyFeedReplyTest(){
        studyFeedReplyRepository.deleteById(8L);
    }


}
