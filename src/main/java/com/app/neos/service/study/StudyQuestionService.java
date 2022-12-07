package com.app.neos.service.study;

import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.entity.study.StudyQuestion;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.study.StudyQuestionCustomRepository;
import com.app.neos.repository.study.StudyQuestionRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyQuestionService {
    private final StudyRepository studyRepository;
    private final StudyCustomRepository studyCustomRepository;
    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyQuestionRepository studyQuestionRepository;
    private final StudyQuestionCustomRepository studyQuestionCustomRepository;


    public void post(StudyQuestionDTO studyQuestionDTO, Long userId, Long studyId){
        StudyQuestionDTO dto = studyQuestionDTO;
        StudyQuestion studyQuestion = dto.toEntity();
        studyQuestion.changeStudyQuestionWriter(userRepository.findById(userId).get());
        studyQuestion.changeStudyId(studyRepository.findById(studyId).get());
        studyQuestionRepository.save(studyQuestion);
    }

    public List<StudyQuestionDTO> getInfo(Long studyId){
        return studyQuestionCustomRepository.findByStudyId(studyId).stream().map(StudyQuestion::toDTO).collect(Collectors.toList());
    }

    public void remove(Long studyQuestionId){
        studyQuestionRepository.deleteById(studyQuestionId);
    }

    @Transactional
    public void update(Long studyQuestionId, String content){
        Optional<StudyQuestion> question = studyQuestionRepository.findById(studyQuestionId);
        if(question.isPresent()){
            StudyQuestion question1 = question.get();
            question1.update(content);
        }
    }

    public StudyQuestionDTO updateDTO(Long id){
        return studyQuestionRepository.findById(id).get().toDTO();
    }

}
