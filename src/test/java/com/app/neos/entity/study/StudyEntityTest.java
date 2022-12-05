package com.app.neos.entity.study;


import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.StudyRecruitStatus;
import com.app.neos.type.study.StudyStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class StudyEntityTest {
    @Autowired
    StudyRepository studyRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest(){
        for(int i = 0 ; i<100 ; i++){
            StudyDTO studyDTO = new StudyDTO();
            studyDTO.setStudyTitle("엑셀의 활용");
            studyDTO.setStudyType("전공");
            studyDTO.setStudyKeyword("음악");
            studyDTO.setStudyO2o("offline");
            studyDTO.setStudyCity("no");
            studyDTO.setStudySupport(9);
            // 내 생각에 이건 화면에서 받아오는 건 아니고 디폴트로 이렇게 들어가야하나?
            studyDTO.setStudyRecruitStatus(StudyRecruitStatus.RECRUITING);
            studyDTO.setStudyStatus(StudyStatus.READY);
            studyDTO.setStudyContent("엑셀이 어려워 하는 분들! 같이 공부해보아요!");

            // 조회수는 디폴트 0이 맞겠지? 그렇기에 nonnull 은 걸어야 할거 같다
            studyDTO.setStudyView(0);
            studyDTO.setStudyEndDate(LocalDate.now().plusMonths(6));

            Study study = studyDTO.toEntity();
            study.changeUser(userRepository.findById(9L).get());
            studyRepository.save(study);
        }

//        studyDTO.setStudyEndDate(study.getCreatedDate().plusMonths(6));

    }


    @Test
    public void updateTest(){
        Study study = studyRepository.findAll().get(0);
        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setStudyTitle("컴활의 활용");
        studyDTO.setStudyType("교양 과목");
        studyDTO.setStudyKeyword("엑셀");
        studyDTO.setStudyO2o("온라인만 가능");
        studyDTO.setStudyCity("신림동");

        study.update(studyDTO);
    }


    @Test
    public void findTest(){
        studyRepository.findAll().stream().map(Study::toString).forEach(log::info);
    }


    @Test
    public void viewUpdateTest(){
        Study study = studyRepository.findById(5L).get();
        study.viewUpdate();
    }
}
