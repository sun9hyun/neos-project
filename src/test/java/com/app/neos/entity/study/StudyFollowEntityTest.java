package com.app.neos.entity.study;

import com.app.neos.repository.study.StudyFollowRepository;
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
public class StudyFollowEntityTest {
    @Autowired
    StudyFollowRepository studyFollowRepository;
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void saveTest(){
        StudyFollowDTO studyFollowDTO = new StudyFollowDTO();
        studyFollowDTO.setStudy(studyRepository.findById(5L).get());
        studyFollowDTO.setUser(userRepository.findById(6L).get());

        StudyFollow studyFollow = new StudyFollow();
        studyFollow.changeStudy(studyFollowDTO.getStudy());
        studyFollow.changeUser(studyFollowDTO.getUser());

        studyFollowRepository.save(studyFollow);
    }

    @Test
    public void findTest(){
        studyFollowRepository.findAll().stream().map(StudyFollow::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        studyFollowRepository.deleteById(9L);
    }
}
