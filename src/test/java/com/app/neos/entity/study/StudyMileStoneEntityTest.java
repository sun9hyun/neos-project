package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyMilestoneDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyMilestoneRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
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
public class StudyMileStoneEntityTest {
    @Autowired
    StudyMilestoneRepository studyMilestoneRepository;
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void saveTest(){
        StudyMilestoneDTO studyMilestoneDTO = new StudyMilestoneDTO();
        studyMilestoneDTO.setStudyMileStoneTitle("오늘 5챕터 마무리");
        studyMilestoneDTO.setStudyMileStoneContent("다들 화이팅!");
        studyMilestoneDTO.setStudyMilestoneStatus(StudyMilestoneStatus.PROCEEDING);

        StudyMilestone studyMilestone = studyMilestoneDTO.toEntity();
        studyMilestoneRepository.save(studyMilestone);

    }

    @Test
    public void updateTest(){
        StudyMilestone studyMilestone = studyMilestoneRepository.findById(12L).get();
        StudyMilestoneDTO studyMilestoneDTO = new StudyMilestoneDTO();
        studyMilestoneDTO.setStudyMileStoneTitle("오늘 5챕터 마무리 완료");
        studyMilestoneDTO.setStudyMileStoneContent("다들 화이팅!");
        studyMilestoneDTO.setStudyMilestoneStatus(StudyMilestoneStatus.FINISH);
        studyMilestone.update(studyMilestoneDTO);
    }

    @Test
    public void findTest(){
        studyMilestoneRepository.findAll().stream().map(StudyMilestone::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        studyMilestoneRepository.deleteById(11L);
    }
}
