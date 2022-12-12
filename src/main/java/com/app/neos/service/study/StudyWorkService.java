package com.app.neos.service.study;

import com.app.neos.domain.study.StudyNewsDTO;
import com.app.neos.domain.study.StudyWorkDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyNews;
import com.app.neos.entity.study.StudyWork;
import com.app.neos.repository.study.*;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.work.StudyLocationStatus;
import com.app.neos.type.study.work.StudyWorkStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudyWorkService {
    private final StudyWorkRepository studyWorkRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudyWorkCustomRepository studyWorkCustomRepository;
    private final StudyNewsRepository studyNewsRepository;
    private final StudyNewsCustomRepository studyNewsCustomRepository;

//    public StudyWork toEntity(){
//        return StudyWork.builder()
//                .studyWorkContent(studyWorkContent)
//                .studyWorkTargetDate(studyWorkTargetDate.atStartOfDay())
//                .studyWorkLocation(studyWorkLocation)
//                .studyWorkStatus(studyWorkStatus)
//                .studyLocationStatus(studyLocationStatus)
//                .build();
//    }

    public void post(StudyWorkDTO studyWorkDTO){

        if(studyWorkDTO.getStudyWorkChoiceMember() !=null){
            studyWorkDTO.setStudyWorkLocation("no");
            studyWorkDTO.setStudyLocationStatus(StudyLocationStatus.ONLINE);
        }

        if(studyWorkDTO.getStudyLocationStatus().equals(StudyLocationStatus.ONLINE)){
            studyWorkDTO.setStudyWorkLocation("no");
        }


        studyWorkDTO.setStudyWorkStatus(StudyWorkStatus.PROCEEDING);

        StudyWork work = studyWorkDTO.toEntity();
        Study study = studyRepository.findById(studyWorkDTO.getStudy().getStudyId()).get();
        work.changeStudy(study);
        work.changeStudyWorkWriter(userRepository.findById(studyWorkDTO.getStudyWorkWriter().getUserId()).get());
        if(studyWorkDTO.getStudyWorkChoiceMember()!=null){
            work.changeStudyWorkChoiceMember(userRepository.findById(studyWorkDTO.getStudyWorkChoiceMember().getUserId()).get());
        }
       StudyWork workEntity =  studyWorkRepository.save(work);


        //뉴스 저장
        StudyNewsDTO dto = new StudyNewsDTO();
        dto.setCategory("work");
        dto.setNewsCreatedTime(workEntity.getCreatedDate().toLocalDate());
        dto.setStatus("proceed");
        StudyNews news = dto.toEntity();
        news.changeStudyWork(workEntity);
        news.changeStudy(study);
        studyNewsRepository.save(news);

    }

    public List<StudyWorkDTO> showProceeding(Long studyId){
        return studyWorkCustomRepository.findAllProceeding(studyId).stream().map(StudyWork::toDTO).collect(Collectors.toList());
    }

    public List<StudyWorkDTO> showFinish(Long studyId){
        return studyWorkCustomRepository.findAllFinish(studyId).stream().map(StudyWork::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void complete(StudyWorkDTO studyWorkDTO){
        studyWorkDTO.setStudyWorkStatus(StudyWorkStatus.FINISH);
        StudyWork work = studyWorkRepository.findById(studyWorkDTO.getStudyWorkId()).get();
        work.statusUpdate(studyWorkDTO);

        StudyNews news = studyNewsCustomRepository.findByStudyIdAndWorkId(work.getStudy().getStudyId(),studyWorkDTO.getStudyWorkId());
        news.update(work.getUpdatedDate().toLocalDate());
    }

}
