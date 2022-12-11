package com.app.neos.service.study;

import com.app.neos.domain.study.StudyMilestoneDTO;
import com.app.neos.entity.study.StudyMilestone;
import com.app.neos.repository.study.StudyMileStoneCustomRepository;
import com.app.neos.repository.study.StudyMilestoneRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class StudyMileStoneService {
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyMilestoneRepository studyMilestoneRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final StudyMileStoneCustomRepository studyMileStoneCustomRepository;

    public void post(Long studyId, StudyMilestoneDTO studyMilestoneDTO, Long userId){
        StudyMilestoneDTO dto = studyMilestoneDTO;
        dto.setStudyMilestoneStatus(StudyMilestoneStatus.PROCEEDING);
        StudyMilestone studyMilestone = dto.toEntity();
        studyMilestone.changeMileStoneWriter(userRepository.findById(userId).get());
        studyMilestone.changeStudy(studyRepository.findById(studyId).get());

        studyMilestoneRepository.save(studyMilestone);

    }

    public List<StudyMilestoneDTO> showListProceeding(Long studyId){
        return studyMileStoneCustomRepository.findAllMileStone(studyId).stream().map(StudyMilestone::toDTO).collect(Collectors.toList());
    }

    public List<StudyMilestoneDTO> showListFinish(Long studyId){
        return studyMileStoneCustomRepository.findAllMileStoneFinish(studyId).stream().map(StudyMilestone::toDTO).collect(Collectors.toList());
    }
}
