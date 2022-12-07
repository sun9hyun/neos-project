package com.app.neos.service.study;

import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.study.StudyQuestionReply;
import com.app.neos.repository.study.StudyQuestionReplyCustomRepository;
import com.app.neos.repository.study.StudyQuestionReplyRepository;
import com.app.neos.repository.study.StudyQuestionRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyQuestionReplyService {
    private final StudyQuestionReplyRepository studyQuestionReplyRepository;
    private final UserRepository userRepository;
    private final StudyQuestionRepository studyQuestionRepository;
    private  final StudyQuestionReplyCustomRepository studyQuestionReplyCustomRepository;
    public void post(Long studyQuestionId, StudyQuestionReplyDTO studyQuestionReplyDTO, Long userId){
        StudyQuestionReplyDTO dto = studyQuestionReplyDTO;
        StudyQuestionReply reply = dto.toEntity();
        reply.changeStudyQuestion(studyQuestionRepository.findById(studyQuestionId).get());
        reply.changeStudyQuestionReplyWriter(userRepository.findById(userId).get());
        studyQuestionReplyRepository.save(reply);
    }

    public List<StudyQuestionReplyDTO> showList(Long studyQuestionId){
        return studyQuestionReplyCustomRepository.findAllByStudyQuestionId(studyQuestionId).stream().map(StudyQuestionReply::toDTO).collect(Collectors.toList());
    }




}
