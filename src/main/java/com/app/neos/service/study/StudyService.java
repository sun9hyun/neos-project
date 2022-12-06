package com.app.neos.service.study;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudySearch;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.study.StudyRecruitStatus;
import com.app.neos.type.study.StudyStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.neos.entity.study.QStudy.study;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyCustomRepository studyCustomRepository;
    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;

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
    public Page<StudyDTO> getBoardList(Pageable pageable, StudySearch studySearch) {
        List<StudyDTO> studyDTOS = studyCustomRepository.findAllPage(pageable,studySearch).map(i->i.toDTO()).toList();
        long total = studyCustomRepository.pageTotal(pageable,studySearch);
        return new PageImpl<>(studyDTOS,pageable,total);
    }

//    @Transactional
//    public Page<Study> getPagination(Pageable pageable){
//        return studyCustomRepository.findAllPage(pageable);
//    }

    @Transactional
    public List<StudyDTO> getStudyListUntilFour(){
        return studyCustomRepository.findUntilFour().stream().map(Study::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public StudyDTO getStudyDTO(Long id){
        Optional<Study> study = studyRepository.findById(id);
        if(study.isPresent()){
            return study.get().toDTO();
        }
        return null;
    }

    public UserDTO getInfo(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get().toDTO();
        }
        return null;
    }
}
