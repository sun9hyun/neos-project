package com.app.neos.service.study;

import com.app.neos.domain.study.StudyMilestoneDTO;
import com.app.neos.domain.study.StudyNewsDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyMilestone;
import com.app.neos.entity.study.StudyNews;
import com.app.neos.repository.study.*;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
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

public class StudyMileStoneService {
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyMilestoneRepository studyMilestoneRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final StudyMileStoneCustomRepository studyMileStoneCustomRepository;
    private final StudyNewsRepository studyNewsRepository;
    private final StudyNewsCustomRepository studyNewsCustomRepository;


    public void post(Long studyId, StudyMilestoneDTO studyMilestoneDTO, Long userId){
        StudyMilestoneDTO dto = studyMilestoneDTO;
        dto.setStudyMilestoneStatus(StudyMilestoneStatus.PROCEEDING);
        Study study = studyRepository.findById(studyId).get();
        StudyMilestone studyMilestone = dto.toEntity();
        studyMilestone.changeMileStoneWriter(userRepository.findById(userId).get());
        studyMilestone.changeStudy(study);

        StudyMilestone milestone = studyMilestoneRepository.save(studyMilestone);

        //뉴스로 저장

        StudyNewsDTO dto1 = new StudyNewsDTO();
        dto1.setCategory("mileStone");
        dto1.setNewsCreatedTime(milestone.getCreatedDate().toLocalDate());
        dto1.setStatus("proceed");
        StudyNews news = dto1.toEntity();
        news.changeStudyMileStone(milestone);
        news.changeStudy(study);
        studyNewsRepository.save(news);
    }

    public List<StudyMilestoneDTO> showListProceeding(Long studyId){
        return studyMileStoneCustomRepository.findAllMileStone(studyId).stream().map(StudyMilestone::toDTO).collect(Collectors.toList());
    }

    public List<StudyMilestoneDTO> showListFinish(Long studyId){
        return studyMileStoneCustomRepository.findAllMileStoneFinish(studyId).stream().map(StudyMilestone::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void complete(Long mileStoneId){
        StudyMilestone milestone = studyMilestoneRepository.findById(mileStoneId).get();
        milestone.complete();
        //뉴스로 저장

        StudyNews news = studyNewsCustomRepository.findByStudyIdAndMileStoneId(milestone.getStudy().getStudyId(),mileStoneId);
        news.update(milestone.getUpdatedDate().toLocalDate());


    }

    @Transactional
    public void update(StudyMilestoneDTO studyMilestoneDTO){
        StudyMilestone milestone = studyMilestoneRepository.findById(studyMilestoneDTO.getStudyMileStoneId()).get();
        milestone.update(studyMilestoneDTO);
    }
}
