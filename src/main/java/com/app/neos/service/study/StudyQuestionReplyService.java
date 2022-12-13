package com.app.neos.service.study;

import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.study.StudyQuestionReply;
import com.app.neos.entity.user.User;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.study.StudyQuestionReplyCustomRepository;
import com.app.neos.repository.study.StudyQuestionReplyRepository;
import com.app.neos.repository.study.StudyQuestionRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final NeosPowerRepository neosPowerRepository;

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


    public void remove(Long studyQuestionReplyId){
        studyQuestionReplyRepository.deleteById(studyQuestionReplyId);
    }

    public Long getStudyQuestionId(Long studyQuestionReplyId){
        return studyQuestionReplyRepository.findById(studyQuestionReplyId).get().toDTO().getStudyQuestion().getStudyQuestionId();
    }

    @Transactional
    public void update(StudyQuestionReplyDTO studyQuestionReplyDTO){
        StudyQuestionReply reply = studyQuestionReplyRepository.findById(studyQuestionReplyDTO.getStudyQuestionReplyId()).get();
        reply.update(studyQuestionReplyDTO);
    }

    @Transactional
    public void expUp(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+10);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(10);
        dto.setNeosPowerContent(NeosPowerContent.REPLY);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }
    @Transactional
    public void expDown(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-10);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(-10);
        dto.setNeosPowerContent(NeosPowerContent.REPLY);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }



}
