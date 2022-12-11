package com.app.neos.service.study;

import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.entity.study.StudyFeedReply;
import com.app.neos.repository.study.StudyFeedReplyCustomRepository;
import com.app.neos.repository.study.StudyFeedReplyRepository;
import com.app.neos.repository.study.StudyFeedRepository;
import com.app.neos.repository.user.UserRepository;
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
public class StudyFeedReplyService {
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyFeedReplyRepository studyFeedReplyRepository;
    private final UserRepository userRepository;
    private final StudyFeedRepository studyFeedRepository;
    private final StudyFeedReplyCustomRepository studyFeedReplyCustomRepository;

    public void post(StudyFeedReplyDTO studyFeedReplyDTO,Long userId, Long studyFeedId){
        StudyFeedReply reply = studyFeedReplyDTO.toEntity();
        reply.changeStudyFeed(studyFeedRepository.findById(studyFeedId).get());
        reply.changeStudyFeedReplyWriter(userRepository.findById(userId).get());
        studyFeedReplyRepository.save(reply);
    }

    public List<StudyFeedReplyDTO> show(Long feedId){
        return studyFeedReplyCustomRepository.findAllFeed(feedId).stream().map(StudyFeedReply::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void remove(Long replyId){
        studyFeedReplyRepository.deleteById(replyId);
    }

    @Transactional
    public void update(StudyFeedReplyDTO studyFeedReplyDTO){
        StudyFeedReply feedReply = studyFeedReplyRepository.findById(studyFeedReplyDTO.getStudyFeedReplyId()).get();
        feedReply.update(studyFeedReplyDTO);
    }

}
