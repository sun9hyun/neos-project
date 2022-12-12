package com.app.neos.service.study;

import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.domain.study.StudyNewsDTO;
import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyMember;
import com.app.neos.entity.study.StudyNews;
import com.app.neos.entity.study.StudySupporter;
import com.app.neos.entity.user.User;
import com.app.neos.repository.study.*;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.member.StudyMemberStatus;
import com.app.neos.type.study.supporter.StudySupporterStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyMemberService {
    private final StudyMemberRepository studyMemberRepository;
    private final StudySupporterRepository studySupporterRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudySupporterCustomRepository studySupporterCustomRepository;
    private final StudyNewsRepository studyNewsRepository;


    public void supportJoin(StudySupporterDTO supporterDTO){
        StudySupporterDTO dto = supporterDTO;
        dto.setStudySupporterStatus(StudySupporterStatus.WAIT);
        StudySupporter supporter = dto.toEntity();
        supporter.changeStudy(studyRepository.findById(dto.getStudyId()).get());
        supporter.changeUser(userRepository.findById(dto.getUser().getUserId()).get());
        studySupporterRepository.save(supporter);
    }

    public List<StudySupporterDTO> showWaitList(){
        return studySupporterCustomRepository.findAllWait().stream().map(StudySupporter::toDTO).collect(Collectors.toList());

    }
    @Transactional
    public void reject(StudySupporterDTO studySupporterDTO){
        StudySupporterDTO dto = studySupporterDTO;
        dto.setStudySupporterStatus(StudySupporterStatus.FAIL);
        StudySupporter studySupporter = studySupporterRepository.findById(studySupporterDTO.getStudySupporterId()).get();
        studySupporter.update(dto);
    }

    @Transactional
    public void ok(StudySupporterDTO studySupporterDTO){
//        패스
        StudySupporterDTO dto = studySupporterDTO;
        dto.setStudySupporterStatus(StudySupporterStatus.PASS);
        StudySupporter studySupporter = studySupporterRepository.findById(studySupporterDTO.getStudySupporterId()).get();
        studySupporter.update(dto);

//        멤버로 조인
        StudyMemberDTO studyMemberDTO = new StudyMemberDTO();
        User user = userRepository.findById(dto.getUser().getUserId()).get();
        Study study = studyRepository.findById(dto.getStudyId()).get();

//        모집 종료 확인
        if(study.getStudyMemberList().size() == study.getStudySupport()-1){
            study.end();
        }

//        저장
        studyMemberDTO.setStudyMemberStatus(StudyMemberStatus.MEMBER);
        StudyMember studyMember = studyMemberDTO.toEntity();
        studyMember.changeUser(user);
        studyMember.changeStudy(study);
       StudyMember member =  studyMemberRepository.save(studyMember);

//        뉴스 저장
        StudyNewsDTO dto1 = new StudyNewsDTO();
        dto1.setCategory("member");
        dto1.setNewsCreatedTime(member.getCreatedDate().toLocalDate());
        dto1.setStatus("proceed");
        StudyNews news = dto1.toEntity();
        news.changeStudyMember(member);
        news.changeStudy(study);
        studyNewsRepository.save(news);

    }

    @Transactional
    public void releaseMember(StudyMemberDTO studyMemberDTO){
        Study study = studyRepository.findById(studyMemberDTO.getStudyId()).get();
        study.start();
        studyMemberRepository.deleteById(studyMemberDTO.getStudyMemberId());
    }
}
