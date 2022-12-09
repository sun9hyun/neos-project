package com.app.neos.service.study;

import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.study.StudySupporter;
import com.app.neos.repository.study.StudyMemberRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.study.StudySupporterRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.supporter.StudySupporterStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyMemberService {
    private final StudyMemberRepository studyMemberRepository;
    private final StudySupporterRepository studySupporterRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;


    public void supportJoin(StudySupporterDTO supporterDTO){
        StudySupporterDTO dto = supporterDTO;
        dto.setStudySupporterStatus(StudySupporterStatus.WAIT);
        StudySupporter supporter = dto.toEntity();
        supporter.changeStudy(studyRepository.findById(dto.getStudyId()).get());
        supporter.changeUser(userRepository.findById(dto.getUser().getUserId()).get());
        studySupporterRepository.save(supporter);
    }
}
