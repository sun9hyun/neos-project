package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.entity.notice.Notice;
import com.app.neos.repository.study.StudyMemberRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.member.StudyMemberStatus;
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
public class StudyMemberEntityTest {
    @Autowired
    StudyMemberRepository studyMemberRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudyRepository studyRepository;


    @Test
    public void saveTest(){
        StudyMemberDTO studyMemberDTO = new StudyMemberDTO();
        studyMemberDTO.setStudyMemberStatus(StudyMemberStatus.LEADER);
        studyMemberDTO.setStudy(studyRepository.findById(5L).get());
        studyMemberDTO.setUser(userRepository.findById(6L).get());

        StudyMember studyMember = studyMemberDTO.toEntity();
        studyMember.changeStudy(studyMemberDTO.getStudy());
        studyMember.changeUser(studyMemberDTO.getUser());

        studyMemberRepository.save(studyMember);
    }

    @Test
    public void findTest(){
        studyMemberRepository.findAll().stream().map(StudyMember::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        studyMemberRepository.deleteById(10L);
    }
}
