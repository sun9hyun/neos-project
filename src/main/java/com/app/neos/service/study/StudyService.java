package com.app.neos.service.study;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.StudyRecruitStatus;
import com.app.neos.type.study.StudyStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyCustomRepository studyCustomRepository;
    private final UserRepository userRepository;

//    글 등록
    public void post(StudyDTO studyDTO){
        StudyDTO study = studyDTO;
        study.setStudyRecruitStatus(StudyRecruitStatus.RECRUITING);
        study.setStudyStatus(StudyStatus.READY);
        study.setStudyEndDate(LocalDate.now().plusMonths(6));

        Study entity = study.toEntity();

        entity.changeUser(userRepository.findById(study.getUserId()).get());
        studyRepository.save(entity);
    }



    @Transactional
    public Page<StudyDTO> getBoardList(Pageable pageable) {
        List<StudyDTO> studyDTOS = studyCustomRepository.findAllPage(pageable).map(i->i.toDTO()).toList();
        return new PageImpl<>(studyDTOS);
    }

    @Transactional
    public Page<Study> getPagination(Pageable pageable){
        return studyCustomRepository.findAllPage(pageable);
    }


}
