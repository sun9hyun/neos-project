package com.app.neos.entity.study;

import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.study.StudySupporterRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.supporter.StudySupporterStatus;
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
public class StudySupporterEntityTest {
    @Autowired
    StudySupporterRepository studySupporterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudyRepository studyRepository;


    @Test
    public void saveTest(){
        StudySupporterDTO studySupporterDTO = new StudySupporterDTO();
        studySupporterDTO.setStudySupporterStatus(StudySupporterStatus.FAIL);
        studySupporterDTO.setUser(userRepository.findAll().get(0));
        studySupporterDTO.setStudy(studyRepository.findAll().get(0));

        StudySupporter studySupporter = studySupporterDTO.toEntity();
        studySupporter.changeUser(studySupporterDTO.getUser());
        studySupporter.changeStudy(studySupporterDTO.getStudy());

        studySupporterRepository.save(studySupporter);
    }


    @Test
    public void updateTest(){
        StudySupporter studySupporter = studySupporterRepository.findById(16L).get();
        StudySupporterDTO studySupporterDTO = new StudySupporterDTO();
        studySupporterDTO.setStudySupporterStatus(StudySupporterStatus.PASS);

        studySupporter.update(studySupporterDTO);

    }

    @Test
    public void findTest(){
        studySupporterRepository.findAll().stream().map(StudySupporter::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        studySupporterRepository.deleteAll();
    }

}
