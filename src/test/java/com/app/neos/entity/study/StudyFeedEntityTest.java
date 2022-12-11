package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyFeedDTO;
import com.app.neos.entity.notice.Notice;
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
public class StudyFeedEntityTest {
    @Autowired
    StudyFeedRepository studyFeedRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudyRepository studyRepository;


    @Test
    public void saveTest(){
        StudyFeedDTO studyFeedDTO = new StudyFeedDTO();
        studyFeedDTO.setStudyFeedContent("오늘 까지 복습 끝내기!");

        StudyFeed studyFeed = studyFeedDTO.toEntity();

        studyFeedRepository.save(studyFeed);
    }

    @Test
    public void updateTest(){
        StudyFeed studyFeed = studyFeedRepository.findById(7L).get();

        StudyFeedDTO studyFeedDTO = new StudyFeedDTO();
        studyFeedDTO.setStudyFeedContent("오늘 까지 예습 끝내기!");

        studyFeed.update(studyFeedDTO);
    }

    @Test
    public void findTest(){
        studyFeedRepository.findAll().stream().map(StudyFeed::toString).forEach(log::info);
    }


    @Test
    public void deleteStudyFeedTest(){
        studyFeedRepository.deleteById(7L);
    }
}
