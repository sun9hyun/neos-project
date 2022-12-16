package com.app.neos.service.study;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudySearch;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.college.QCollege;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
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

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
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
    private final NeosPowerRepository neosPowerRepository;

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
    public void viewUpdate(Long studyId){
        Study study = studyRepository.findById(studyId).get();
        study.viewUpdate();
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

    public List<String> collegeName(){
        List<College> collegeList = jpaQueryFactory.selectFrom(QCollege.college).fetch();
        List<String> collegeNames = collegeList.stream().map(College::getCollegeName).collect(Collectors.toList());
        collegeNames.sort(Comparator.naturalOrder());

        return collegeNames;
    }

    public int minusDay(StudyDTO studyDTO){
        LocalDate startDay = studyDTO.getStudyStartDate();
        LocalDate endDay = studyDTO.getStudyEndDate();

        LocalDateTime start = startDay.atStartOfDay();
        LocalDateTime end = endDay.atStartOfDay();
        int betweenDays = (int) Duration.between(start, end).toDays();
        return betweenDays;
    }

    public UserDTO nowWriter(Long userId){
        return userRepository.findById(userId).get().toDTO();
    }

    public void remove(Long studyId){
        studyRepository.delete(studyRepository.findById(studyId).get());
    }

    @Transactional
    public void update(StudyDTO studyDTO){
        Study study = studyRepository.findById(studyDTO.getStudyId()).get();
        study.update(studyDTO);
    }

    @Transactional
    public void supportUpdate(Long studyId, int studySupport){
        Study study = studyRepository.findById(studyId).get();
        study.supportUpdate(studySupport);
    }

    @Transactional
    public void supportEnd(Long studyId){
        Study study = studyRepository.findById(studyId).get();
        study.supportEnd();
    }

    @Transactional
    public void postEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+100);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(100);
        dto.setNeosPowerContent(NeosPowerContent.POST);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }
    @Transactional
    public void postDeleteEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-100);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(-100);
        dto.setNeosPowerContent(NeosPowerContent.POST);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }

    @Transactional
    public List<StudyDTO> myList(Long userId){
        return studyCustomRepository.findMyStudyList(userId).stream().map(Study::toDTO).collect(Collectors.toList());
    }
}
