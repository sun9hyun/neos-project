package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyWorkDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.study.StudyWorkRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.work.StudyLocationStatus;
import com.app.neos.type.study.work.StudyWorkStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class StudyWorkEntityTest {
    @Autowired
    StudyWorkRepository studyWorkRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudyRepository studyRepository;

    @Test
    public void saveTest(){
        StudyWorkDTO studyWorkDTO = new StudyWorkDTO();
        studyWorkDTO.setStudyWorkContent("오메가호근몬 토벌");
        studyWorkDTO.setStudyLocationStatus(StudyLocationStatus.ONLINE);
        studyWorkDTO.setStudyWorkStatus(StudyWorkStatus.PROCEEDING);

        StudyWork studyWork = studyWorkDTO.toEntity();

        studyWorkRepository.save(studyWork);
    }


    @Test
    public void updateTest(){
        StudyWork studyWork = studyWorkRepository.findAll().get(0);
        StudyWorkDTO studyWorkDTO = new StudyWorkDTO();
        studyWorkDTO.setStudyWorkStatus(StudyWorkStatus.FINISH);
        studyWork.statusUpdate(studyWorkDTO);
    }

    @Test
    public void findTest(){
        studyWorkRepository.findAll().stream().map(StudyWork::toString).forEach(log::info);
    }


    @Test
    public void deleteTest(){
        studyWorkRepository.deleteAll();
    }


}
